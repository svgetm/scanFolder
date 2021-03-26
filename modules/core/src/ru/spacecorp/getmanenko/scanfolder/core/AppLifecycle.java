package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.global.Events;
import com.haulmont.cuba.core.sys.events.AppContextInitializedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;


@Component(AppLifecycle.NAME)
public class AppLifecycle {
    public static final String NAME = "scanfolder_AppLifecycle";


    @EventListener(AppContextInitializedEvent.class)
    @Order(Events.LOWEST_PLATFORM_PRECEDENCE + 100)
    public void initStartListeners() {


    }
}