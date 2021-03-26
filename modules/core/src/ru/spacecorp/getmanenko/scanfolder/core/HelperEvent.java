package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.listener.AfterInsertEntityListener;
import org.springframework.stereotype.Component;
import ru.spacecorp.getmanenko.scanfolder.UiNotificationEvent;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;

import javax.inject.Inject;
import java.sql.Connection;

@Component(HelperEvent.NAME)
public class HelperEvent implements AfterInsertEntityListener<ListAttributes> {
    public static final String NAME = "scanfolder_HelperEvent";
    @Inject
    private Events events;

    @Override
    public void onAfterInsert(ListAttributes entity, Connection connection) {
        events.publish(new UiNotificationEvent(this));
    }

}