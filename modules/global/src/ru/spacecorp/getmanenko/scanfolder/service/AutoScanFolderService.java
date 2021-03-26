package ru.spacecorp.getmanenko.scanfolder.service;

import java.io.IOException;
import java.nio.file.WatchService;

public interface AutoScanFolderService {
    String NAME = "scanfolder_AutoScanFolderService";

    void startThread(String nameSettings);

    void stopWatchService(String dir) throws IOException;


}