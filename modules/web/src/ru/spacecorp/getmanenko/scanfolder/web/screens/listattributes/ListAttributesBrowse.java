package ru.spacecorp.getmanenko.scanfolder.web.screens.listattributes;

import com.haulmont.cuba.core.entity.Entity;
import com.haulmont.cuba.core.global.Messages;
import com.haulmont.cuba.gui.Notifications;
import com.haulmont.cuba.gui.components.LookupField;
import com.haulmont.cuba.gui.components.Timer;
import com.haulmont.cuba.gui.export.ExportDisplay;
import com.haulmont.cuba.gui.model.CollectionLoader;
import com.haulmont.cuba.gui.screen.*;
import com.haulmont.cuba.gui.screen.LookupComponent;
import org.springframework.context.event.EventListener;
import ru.spacecorp.getmanenko.scanfolder.UiNotificationEvent;
import ru.spacecorp.getmanenko.scanfolder.entity.ExpansionSettings;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;
import ru.spacecorp.getmanenko.scanfolder.entity.Settings;
import ru.spacecorp.getmanenko.scanfolder.service.ScanFolderService;


import javax.inject.Inject;
import java.io.IOException;
import java.util.Objects;


@UiController("scanfolder_ListAttributes.browse")
@UiDescriptor("list-attributes-browse.xml")
@LookupComponent("listAttributesesTable")
@LoadDataBeforeShow

public class ListAttributesBrowse extends StandardLookup<ListAttributes> {

    @Inject
    private ScanFolderService scanFolderService;
    @Inject
    private Notifications notifications;
    @Inject
    private CollectionLoader<ListAttributes> listAttributesesDl;
    @Inject
    private Messages messages;
    @Inject
    private ExportDisplay exportDisplay;
    @Inject
    private LookupField<Settings> folderPath;
    @Inject
    private LookupField<ExpansionSettings> sortField;

    @EventListener
    public void onUiNotificationEvent(UiNotificationEvent event) {
        listAttributesesDl.load();
        notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "tableRefresh"))
                .show();
    }

    @Subscribe("timerUpdate")
    private void onTimer(Timer.TimerActionEvent event) {
        listAttributesesDl.load();
        notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder","tableRefresh"))
                .withPosition(Notifications.Position.TOP_RIGHT).show();
    }


    public void refreshList() throws IOException {
        if (folderPath.getValue() != null) {
            scanFolderService.findAndLastTimeCheck(folderPath.getValue().getName());
            listAttributesesDl.load();
            notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "tableRefresh"))
                    .withPosition(Notifications.Position.TOP_RIGHT).show();
        } else notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "noSelect"))
                .withPosition(Notifications.Position.MIDDLE_CENTER).show();
    }

    public void openFilesExplorer(Entity item, String columnId) throws IOException {
        ListAttributes listAttributes = (ListAttributes) item;
        Runtime.getRuntime().exec("explorer.exe /select," + listAttributes.getLinkDownload());

    }

    public void saveFile(Entity item, String columnId) {
        exportDisplay.show(Objects.requireNonNull(item.getValue("fileDesc")));
    }

    public void sortByExp() {

        if (sortField.getValue() != null) {
            String query = String.format("select e from scanfolder_ListAttributes e where e.extension = '%s'", sortField.getValue().getExpansion());
            listAttributesesDl.setQuery(query);
            listAttributesesDl.load();
        } else {

            notifications.create().withCaption(messages.getMessage("ru.spacecorp.getmanenko.scanfolder", "noExp"))
                    .withPosition(Notifications.Position.MIDDLE_CENTER).show();
        }

    }

    public void lookAll() {
        listAttributesesDl.setQuery("select e from scanfolder_ListAttributes e");
        listAttributesesDl.load();
    }
}