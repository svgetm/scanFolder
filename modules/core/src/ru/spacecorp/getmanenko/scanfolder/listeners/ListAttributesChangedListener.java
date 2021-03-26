package ru.spacecorp.getmanenko.scanfolder.listeners;

import com.haulmont.cuba.core.app.FileStorageAPI;
import com.haulmont.cuba.core.app.events.EntityChangedEvent;
import com.haulmont.cuba.core.global.DataManager;
import com.haulmont.cuba.core.global.FileStorageException;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;

import javax.inject.Inject;
import java.io.*;
import java.nio.file.Files;
import java.util.UUID;

@Component("scanfolder_ListAttributesChangedListener")
public class ListAttributesChangedListener {

    @Inject
    private DataManager dataManager;
    @Inject
    private FileStorageAPI fileStorageAPI;

    private ListAttributes getListAtr(UUID id) {
        return dataManager.load(ListAttributes.class).id(id).view("listAttributes-view").one();
    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    public void afterCommit(EntityChangedEvent<ListAttributes, UUID> event) throws IOException, FileStorageException {

        EntityChangedEvent.Type typeEvent = event.getType();
        if (typeEvent == EntityChangedEvent.Type.CREATED) {

            ListAttributes listAttributes = getListAtr(event.getEntityId().getValue());

            File file = new File(listAttributes.getLinkDownload());
            byte[] data = Files.readAllBytes(file.toPath());
            fileStorageAPI.saveFile(listAttributes.getFileDesc(),data);

        }
    }
}