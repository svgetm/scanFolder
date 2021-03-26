package ru.spacecorp.getmanenko.scanfolder.service;

import java.io.File;
import java.io.IOException;

public interface ManualScanService {
    String NAME = "scanfolder_ManualScanService";
    void scanAllFolder(File folder, String name) throws IOException;
}