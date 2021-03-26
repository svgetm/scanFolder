package ru.spacecorp.getmanenko.scanfolder.web.screens.settings;

import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.core.global.Metadata;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.Button;
import com.haulmont.cuba.gui.components.GroupTable;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.Table;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import org.springframework.context.event.EventListener;
import ru.spacecorp.getmanenko.scanfolder.*;
import ru.spacecorp.getmanenko.scanfolder.entity.ExpansionSettings;
import ru.spacecorp.getmanenko.scanfolder.entity.FailNameFiles;
import ru.spacecorp.getmanenko.scanfolder.entity.Settings;
import ru.spacecorp.getmanenko.scanfolder.service.AutoScanFolderService;

import javax.inject.Inject;

@UiController("scanfolder_Settings.browse")
@UiDescriptor("settings-browse.xml")
@LookupComponent("settingsesTable")
@LoadDataBeforeShow
public class SettingsBrowse extends StandardLookup<Settings> {

    @Inject
    private CollectionLoader<ExpansionSettings> expansionSettingsesDl;
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;
    @Inject
    private LookupField<ExpansionSettings> lookExp;
    @Inject
    private LookupField<FailNameFiles> lookFailName;
    @Inject
    private CollectionLoader<FailNameFiles> failNameFilesesDl;
    @Inject
    private GroupTable<Settings> settingsesTable;
    @Inject
    private AutoScanFolderService autoScanFolderService;
    @Inject
    private Notifications notifications;
    @Inject
    private Button startScan;
    @Inject
    private Messages messages;
    @Inject
    private CollectionLoader<Settings> settingsesDl;
    @Inject
    private Button stopScan;

    @Subscribe
    public void onInit(InitEvent event) {
        startScan.setEnabled(false);
        stopScan.setEnabled(false);
    }

    @Subscribe("settingsesTable")
    public void onSettingsesTableSelection(Table.SelectionEvent<Settings> event) {

        startScan.setEnabled(!settingsesTable.getSingleSelected().getScaning());
        stopScan.setEnabled(true);

    }


    @EventListener
    public void onUserStopThreadEvent(UserStopThreadEvent event) {
        startScan.setEnabled(true);
    }

    @EventListener
    public void onNewFolderEvent(NewFolderEvent event) {
        notifications.create().withCaption("Изменились настройки, перезапустите сканирование").withPosition(Notifications.Position.MIDDLE_CENTER).show();
    }

    @EventListener
    public void onUpdateFolderEvent(FolderIsCorrect event) {
        notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "folderIsNotCorrect"))
                .withStyleName("danger").withPosition(Notifications.Position.MIDDLE_CENTER).show();
    }

    @EventListener
    public void onStartScanFolderEvent(StartScanEvent event) {
        notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "folderStartScan"))
                .withStyleName("friendly").withPosition(Notifications.Position.MIDDLE_CENTER).show();
        startScan.setEnabled(false);
    }


    @EventListener
    public void onUiNotificationEvent(CrashThreadEvent event) {
        autoScanFolderService.startThread(event.getName());
    }

    @Install(to = "lookExp", subject = "newOptionHandler")
    private void lookExpNewOptionHandler(String string) {

        ExpansionSettings expansionSettings = metadata.create(ExpansionSettings.class);
        expansionSettings.setExpansion(string);
        dataManager.commit(expansionSettings);
        lookExp.setValue(null);
        expansionSettingsesDl.load();

    }

    @Install(to = "lookFailName", subject = "newOptionHandler")
    private void lookFailNameNewOptionHandler(String string) {
        FailNameFiles failNameFiles = metadata.create(FailNameFiles.class);
        failNameFiles.setNameFile(string);
        dataManager.commit(failNameFiles);
        lookFailName.setValue(null);
        failNameFilesesDl.load();

    }

    public void startThreadScan() {
        if (!settingsesTable.getSingleSelected().getWork()) {
            notifications.create().withCaption("Настройка не активна").withPosition(Notifications.Position.MIDDLE_CENTER).show();
        }else {
            autoScanFolderService.startThread(settingsesTable.getSingleSelected().getName());
            settingsesTable.getSingleSelected().setScaning(true);
            dataManager.commit(settingsesTable.getSingleSelected());
            startScan.setEnabled(false);
        }
    }

    public void stopThread() {
        settingsesTable.getSingleSelected().setWork(!settingsesTable.getSingleSelected().getWork());
        settingsesTable.getSingleSelected().setScaning(false);
        dataManager.commit(settingsesTable.getSingleSelected());
        settingsesDl.load();

    }
}