package ru.spacecorp.getmanenko.scanfolder.web.screens.settings;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.Settings;

@UiController("scanfolder_Settings.edit")
@UiDescriptor("settings-edit.xml")
@EditedEntityContainer("settingsDc")
@LoadDataBeforeShow
public class SettingsEdit extends StandardEditor<Settings> {

}