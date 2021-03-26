package ru.spacecorp.getmanenko.scanfolder;

import com.haulmont.addon.globalevents.GlobalApplicationEvent;
import com.haulmont.addon.globalevents.GlobalUiEvent;

public class UiNotificationEvent extends GlobalApplicationEvent implements GlobalUiEvent {
    /**
     * Create a new ApplicationEvent.
     *
     * @param source the object on which the event initially occurred (never {@code null})
     */
    public UiNotificationEvent(Object source) {
        super(source);
    }
}
