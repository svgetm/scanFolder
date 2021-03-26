package ru.spacecorp.getmanenko.scanfolder.web.screens.failnamefiles;

import com.haulmont.cuba.gui.screen.*;
import ru.spacecorp.getmanenko.scanfolder.entity.FailNameFiles;

@UiController("scanfolder_FailNameFiles.edit")
@UiDescriptor("fail-name-files-edit.xml")
@EditedEntityContainer("failNameFilesDc")
@LoadDataBeforeShow
public class FailNameFilesEdit extends StandardEditor<FailNameFiles> {
}