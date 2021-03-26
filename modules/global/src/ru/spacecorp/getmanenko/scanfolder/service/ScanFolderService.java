package ru.spacecorp.getmanenko.scanfolder.service;

import java.io.IOException;

public interface ScanFolderService {
    String NAME = "scanfolder_ScanFolderService";
    void findAndLastTimeCheck(String name) throws IOException;
}