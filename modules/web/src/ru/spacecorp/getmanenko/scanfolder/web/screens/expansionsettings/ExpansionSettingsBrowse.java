package ru.spacecorp.getmanenko.scanfolder.web.screens.expansionsettings;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.ExpansionSettings;

@UiController("scanfolder_ExpansionSettings.browse")
@UiDescriptor("expansion-settings-browse.xml")
@LookupComponent("expansionSettingsesTable")
@LoadDataBeforeShow
public class ExpansionSettingsBrowse extends StandardLookup<ExpansionSettings> {
}