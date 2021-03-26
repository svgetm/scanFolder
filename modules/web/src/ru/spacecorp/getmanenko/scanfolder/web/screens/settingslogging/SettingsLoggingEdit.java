package ru.spacecorp.getmanenko.scanfolder.web.screens.settingslogging;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.SettingsLogging;

@UiController("scanfolder_SettingsLogging.edit")
@UiDescriptor("settings-logging-edit.xml")
@EditedEntityContainer("settingsLoggingDc")
@LoadDataBeforeShow
public class SettingsLoggingEdit extends StandardEditor<SettingsLogging> {
}