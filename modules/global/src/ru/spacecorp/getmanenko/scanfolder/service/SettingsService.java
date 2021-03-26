package ru.spacecorp.getmanenko.scanfolder.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import ru.spacecorp.getmanenko.scanfolder.entity.ExpansionSettings;
import ru.spacecorp.getmanenko.scanfolder.entity.FailNameFiles;

import java.util.Date;
import java.util.HashMap;
import java.util.List;

public interface SettingsService {
    String NAME = "scanfolder_SettingsService";

    String getDir(String name);

    Date getLastUpTime(String name);

    void setLastUpTime(Date date,String name);

    FileDescriptor getFD();

    String getLink();

    boolean checkNameFile(String nameFile, String nameSettings);

    boolean checkExpFile(String expFile, String nameSettings);

    HashMap<String, String> getTextLog();

    boolean getActive(String nameSettings);
}