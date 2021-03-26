package ru.spacecorp.getmanenko.scanfolder.service;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.LoadContext;
import org.springframework.stereotype.Service;
import ru.spacecorp.getmanenko.scanfolder.entity.*;

import javax.inject.Inject;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;


@Service(SettingsService.NAME)
public class SettingsServiceBean implements SettingsService {

    @Inject
    private DataManager dataManager;

    private Settings getSettings(String nameSettings) {
        LoadContext<Settings> context = LoadContext.create(Settings.class);
        context.setQuery(LoadContext.createQuery("select e from scanfolder_Settings e where e.name = :name")
                .setParameter("name", nameSettings))
                .setView("settings-view");
        return dataManager.load(context);
    }

    private ListAttributes getListAttributesSettings() {
        LoadContext<ListAttributes> ctx = LoadContext.create(ListAttributes.class);
        ctx.setQuery(LoadContext.createQuery("select e from scanfolder_ListAttributes e"));
        return dataManager.load(ctx);
    }

    public List<ExpansionSettings> getExtSettings() {
        return dataManager.load(ExpansionSettings.class)
                .query("select e from scanfolder_ExpansionSettings e")
                .list();
    }

    public List<FailNameFiles> getFailNameSettings() {
        return dataManager.load(FailNameFiles.class)
                .query("select e from scanfolder_FailNameFiles e")
                .list();
    }

    public List<SettingsLogging> getTextForLogging() {
        return dataManager.load(SettingsLogging.class)
                .query("select e from scanfolder_SettingsLogging e")
                .list();
    }

    @Override
    public String getLink() {
        return getListAttributesSettings().getLinkDownload();
    }

    @Override
    public String getDir(String name) {
        return getSettings(name).getFolederPath();
    }

    @Override
    public Date getLastUpTime(String name) {
        return getSettings(name).getLastTimeUpdate();
    }

    @Override
    public void setLastUpTime(Date date, String name) {
        Settings settings = getSettings(name);
        settings.setLastTimeUpdate(date);
        dataManager.commit(settings);
    }

    @Override
    public FileDescriptor getFD() {
        return getListAttributesSettings().getFileDesc();
    }

    @Override
    public boolean checkNameFile(String nameFile, String nameSettings) {
        return getSettings(nameSettings).getIgnoreFiles()
                .stream()
                .map(name -> name.getNameFile().toLowerCase())
                .collect(Collectors.toList())
                .contains(nameFile.toLowerCase());
    }

    @Override
    public boolean checkExpFile(String expFile, String nameSettings) {
        return getSettings(nameSettings).getExpansion()
                .stream()
                .map(exp -> exp.getExpansion().toLowerCase())
                .collect(Collectors.toList())
                .contains(expFile.toLowerCase());
    }

    @Override
    public HashMap<String, String> getTextLog() {

        return (HashMap<String, String>) getTextForLogging()
                .stream()
                .collect(Collectors.toMap(SettingsLogging::getKey, SettingsLogging::getText));
    }

    @Override
    public boolean getActive(String nameSettings) {
        return getSettings(nameSettings).getWork();
    }
}