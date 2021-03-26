package ru.spacecorp.getmanenko.scanfolder.service;


import com.haulmont.cuba.core.global.AppBeans;
import com.haulmont.cuba.core.global.Events;
import org.springframework.stereotype.Service;
import ru.spacecorp.getmanenko.scanfolder.FolderIsCorrect;
import ru.spacecorp.getmanenko.scanfolder.core.CheckerIsCorrectFolder;
import ru.spacecorp.getmanenko.scanfolder.core.ScanFolder;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;

@Service(AutoScanFolderService.NAME)
public class AutoScanFolderServiceBean implements AutoScanFolderService {

    @Inject
    private CheckerIsCorrectFolder checkerIsCorrectFolder;
    @Inject
    private SettingsService settingsService;
    @Inject
    private Events events;

    @Override
    public void startThread(String nameSettings) {

        if (checkerIsCorrectFolder.availabilityCheck(settingsService.getDir(nameSettings))){
            ScanFolder scanFolder = AppBeans.getPrototype("scanfolder_ScanFolder", nameSettings);
            scanFolder.runThread();
        }
        else {
            events.publish(new FolderIsCorrect(this));
        }
    }

    @Override
    public void stopWatchService(String dir) throws IOException {

        File fileLog = new File(String.format("%s%s.watchService", dir, "stop"));
        fileLog.createNewFile();
        fileLog.delete();

    }

}