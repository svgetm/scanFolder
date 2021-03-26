package ru.spacecorp.getmanenko.scanfolder.web.screens.failnamefiles;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.FailNameFiles;

@UiController("scanfolder_FailNameFiles.browse")
@UiDescriptor("fail-name-files-browse.xml")
@LookupComponent("failNameFilesesTable")
@LoadDataBeforeShow
public class FailNameFilesBrowse extends StandardLookup<FailNameFiles> {
}