package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.sys.AppContext;
import com.haulmont.cuba.core.sys.SecurityContext;
import com.haulmont.cuba.security.app.Authentication;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import ru.spacecorp.getmanenko.logservice.service.LogService;
import ru.spacecorp.getmanenko.scanfolder.CrashThreadEvent;
import ru.spacecorp.getmanenko.scanfolder.StartScanEvent;
import ru.spacecorp.getmanenko.scanfolder.UserStopThreadEvent;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;
import ru.spacecorp.getmanenko.scanfolder.service.ManualScanService;
import ru.spacecorp.getmanenko.scanfolder.service.SettingsService;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.*;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

import static java.nio.file.StandardWatchEventKinds.*;

@Component(ScanFolder.NAME)
public class ScanFolder {
    public static final String NAME = "scanfolder_ScanFolder";

    @Inject
    private DataManager dataManager;
    @Inject
    private Authentication authentication;
    @Inject
    private LogService logService;
    @Inject
    private DbUpdateWorker dbUpdateWorker;
    @Inject
    private SettingsService settingsService;
    @Inject
    private ManualScanService manualScanService;
    @Inject
    private Events events;
    private String name;
    private WatchService watchService;

    public ScanFolder(String name) {
        this.name = name;
    }

    private Thread thread = new Thread(new Runnable() {
        @Override
        public void run() {
            initThread(name);
        }

    });

    public void runThread() {
        thread.start();
        events.publish(new StartScanEvent(this));
    }

    private void initThread(String name) {
        final SecurityContext securityContext = AppContext.getSecurityContext();
        AppContext.setSecurityContext(securityContext);

        authentication.begin("admin");
        try {
            autoScanFolder(settingsService.getDir(name), watchService = FileSystems.getDefault().newWatchService(), name);
        } catch (Exception ignore) {
            System.out.println("Watch Service CLOSE");
        } finally {
            if (settingsService.getActive(name)) {
                authentication.end();
                events.publish(new CrashThreadEvent(this, name));
            }else {
                events.publish(new UserStopThreadEvent(this));
            }
        }
    }

    public void autoScanFolder(String directory, java.nio.file.WatchService watchService, String name) throws IOException {

        Path dirPath = Paths.get(directory);

        dirPath.register(watchService, ENTRY_CREATE, ENTRY_MODIFY);

        while (settingsService.getActive(name)) {
            WatchKey key;

            try {
                key = watchService.take();
            } catch (InterruptedException e) {
                thread.interrupt();
                e.printStackTrace();
                return;
            }

            for (WatchEvent<?> event : key.pollEvents()) {
                Path fileName = ((WatchEvent<Path>) event).context();
                File newFile = new File(directory + fileName.getFileName());
                ListAttributes contextLoad = dataManager.load(dbUpdateWorker.contLoad(newFile));

                if (FilenameUtils.getExtension(newFile.getName()).equals("watchService")){
                    break;
                }

                if (event.kind() == OVERFLOW) {
                    manualScanService.scanAllFolder(new File(settingsService.getDir(name)), name);
                    settingsService.setLastUpTime(new Date(System.currentTimeMillis()), name);

                } else {

                    if (event.kind() == ENTRY_CREATE) {
                        if (!settingsService.checkNameFile(newFile.getName(), name)) {
                            if (settingsService.checkExpFile(FilenameUtils.getExtension(newFile.getName()), name)) {
                                if (contextLoad == null) {
                                    dbUpdateWorker.addEntityBD(newFile, Paths.get(newFile.getAbsolutePath()));
                                    logService.writerFilesAutoScan(" " + settingsService.getTextLog().get("Success") + " - ",
                                            directory, newFile.getName(), System.currentTimeMillis());

                                } else if (contextLoad.getName().equals(newFile.getName()) &&
                                        contextLoad.getDataMod().getTime() == newFile.lastModified()) {
                                    logService.writerFilesAutoScan(" " + settingsService.getTextLog().get("Duplicate") + " - ",
                                            directory, newFile.getName(), System.currentTimeMillis());
                                }

                            } else if (!settingsService.checkExpFile(FilenameUtils.getExtension(newFile.getName()), name) &&
                                    !FilenameUtils.getExtension(newFile.getName()).equals("txt")) {
                                logService.writerFilesAutoScan(" " + settingsService.getTextLog().get("Outsider") + " - ",
                                        directory, newFile.getName(), System.currentTimeMillis());
                            }
                        }


                    } else if (event.kind() == ENTRY_MODIFY) {
                        if (settingsService.checkExpFile(FilenameUtils.getExtension(newFile.getName()), name)) {
                            BasicFileAttributes attr = Files.readAttributes(Paths.get(newFile.getAbsolutePath()), BasicFileAttributes.class);
                            if (contextLoad.getName().equals(newFile.getName())) {
                                if (contextLoad.getDataCreate().getTime() != attr.creationTime().toMillis() &&
                                        contextLoad.getDataMod().getTime() != newFile.lastModified()) {
                                    logService.writerFilesAutoScan(" " + settingsService.getTextLog().get("Duplicate") + " - ",
                                            directory, newFile.getName(), System.currentTimeMillis());
                                } else if (contextLoad.getDataCreate().getTime() == attr.creationTime().toMillis() &&
                                        contextLoad.getDataMod().getTime() != newFile.lastModified()) {
                                    logService.writerFilesAutoScan(" " + settingsService.getTextLog().get("Duplicate") + " - ",
                                            directory, newFile.getName(), System.currentTimeMillis());
                                }
                            }
                        }
                    }
                }
            }
            if (!key.reset()) {
                break;
            }
        }
    }

}