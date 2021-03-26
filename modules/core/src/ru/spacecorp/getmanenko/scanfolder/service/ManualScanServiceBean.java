package ru.spacecorp.getmanenko.scanfolder.service;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Service;
import ru.spacecorp.getmanenko.logservice.service.LogService;
import ru.spacecorp.getmanenko.scanfolder.FolderIsCorrect;
import ru.spacecorp.getmanenko.scanfolder.core.CheckerIsCorrectFolder;
import ru.spacecorp.getmanenko.scanfolder.core.DbUpdateWorker;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Paths;
import java.util.stream.Collectors;

@Service(ManualScanService.NAME)
public class ManualScanServiceBean implements ManualScanService {

    @Inject
    private CheckerIsCorrectFolder checkerIsCorrectFolder;
    @Inject
    private SettingsService settingsService;
    @Inject
    private DataManager dataManager;
    @Inject
    private DbUpdateWorker dbUpdateWorker;
    @Inject
    private LogService logService;
    @Inject
    private Events events;


    @Override
    public void scanAllFolder(File folder, String name) throws IOException {
        checkerIsCorrectFolder.availabilityCheck(String.valueOf(folder.getAbsoluteFile()));

        if (checkerIsCorrectFolder.availabilityCheck(String.valueOf(folder.getAbsoluteFile()))){

            File[] file = folder.listFiles();

            for (int i = 0; i < file.length; i++) {

                if (file[i].isDirectory()) {
                    scanAllFolder(file[i],name);

                } else if (!settingsService.checkNameFile(file[i].getName(), name)){

                    if (settingsService.checkExpFile(FilenameUtils.getExtension(file[i].getName()), name)) {

                        if (settingsService.getLastUpTime(name).getTime() < file[i].lastModified()) {

                            ListAttributes contextLoad = dataManager.load(dbUpdateWorker.contLoad(file[i]));

                            if (contextLoad == null) {

                                dbUpdateWorker.addEntityBD(file[i], Paths.get(file[i].getAbsolutePath()));
                                logService.writerFilesManualScan(" " + settingsService.getTextLog().get("Success") +" - ",
                                        settingsService.getDir(name), file[i].getName(), settingsService.getLastUpTime(name).getTime());


                            } else if (contextLoad.getName().equals(file[i].getName()) && !contextLoad
                                    .getDataMod().equals(file[i].lastModified())) {

                                logService.writerFilesManualScan(" " + settingsService.getTextLog().get("Duplicate") +" - ",
                                        settingsService.getDir(name), file[i].getName(), settingsService.getLastUpTime(name).getTime());

                            } else logService.writerFilesManualScan(" Error - ",
                                    settingsService.getDir(name), file[i].getName(), settingsService.getLastUpTime(name).getTime());
                        }

                    } else if (!settingsService.checkExpFile(FilenameUtils.getExtension(file[i].getName()), name) &&
                            !FilenameUtils.getExtension(file[i].getName()).equals("txt")) {

                        logService.writerFilesManualScan(" " + settingsService.getTextLog().get("Outsider") +" - ",
                                settingsService.getDir(name), file[i].getName(), settingsService.getLastUpTime(name).getTime());
                    }
                }
            }
        }
        else {
            events.publish(new FolderIsCorrect(this));
        }


    }
}