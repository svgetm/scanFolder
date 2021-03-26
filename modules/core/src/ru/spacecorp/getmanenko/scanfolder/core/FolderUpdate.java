package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.spacecorp.getmanenko.scanfolder.entity.Settings;
import ru.spacecorp.getmanenko.scanfolder.service.AutoScanFolderService;

import javax.inject.Inject;

import java.io.IOException;
import java.util.UUID;

@Component(FolderUpdate.NAME)
public class FolderUpdate  {
    public static final String NAME = "scanfolder_FolderUpdate";
    @Inject
    private AutoScanFolderService autoScanFolderService;
    @Inject
    private DataManager dataManager;

    private Settings getSettings(UUID id) {
        return dataManager.load(Settings.class).id(id).view("settings-view").one();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void beforeCommit(EntityChangedEvent<Settings, UUID> event) throws IOException {

        EntityChangedEvent.Type typeEvent = event.getType();

//        if (typeEvent == EntityChangedEvent.Type.UPDATED){
//
//            Settings settings = getSettings(event.getEntityId().getValue());
//            autoScanFolderService.startThread(settings.getName());
//
//        }
    }

}