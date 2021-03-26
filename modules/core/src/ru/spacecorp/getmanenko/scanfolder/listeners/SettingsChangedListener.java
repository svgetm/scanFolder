package ru.spacecorp.getmanenko.scanfolder.listeners;

import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.Events;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.spacecorp.getmanenko.scanfolder.NewFolderEvent;
import ru.spacecorp.getmanenko.scanfolder.entity.Settings;
import ru.spacecorp.getmanenko.scanfolder.service.AutoScanFolderService;

import javax.inject.Inject;
import java.io.IOException;
import java.util.UUID;

@Component("scanfolder_SettingsChangedListener")
public class SettingsChangedListener {


    @Inject
    private Events events;
    @Inject
    private DataManager dataManager;
    @Inject
    private AutoScanFolderService autoScanFolderService;

    private Settings getSettings(UUID id) {
        return dataManager.load(Settings.class).id(id).view("settings-view").one();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<Settings, UUID> event) throws IOException {

        EntityChangedEvent.Type typeEvent = event.getType();
        if (typeEvent == EntityChangedEvent.Type.UPDATED) {

            Settings settings = getSettings(event.getEntityId().getValue());

            if (!settings.getWork()) {

                autoScanFolderService.stopWatchService(settings.getFolederPath());

            }
//            events.publish(new NewFolderEvent(this));
        }

    }
}