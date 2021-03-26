package ru.spacecorp.getmanenko.scanfolder.web.screens.expansionsettings;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.ExpansionSettings;

@UiController("scanfolder_ExpansionSettings.edit")
@UiDescriptor("expansion-settings-edit.xml")
@EditedEntityContainer("expansionSettingsDc")
@LoadDataBeforeShow
public class ExpansionSettingsEdit extends StandardEditor<ExpansionSettings> {
}