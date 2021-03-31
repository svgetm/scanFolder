package ru.spacecorp.getmanenko.scanfolder.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "SCANFOLDER_SETTINGS_LOGGING")
@Entity(name = "scanfolder_SettingsLogging")
@NamePattern("%s|keyLocal")
public class SettingsLogging extends StandardEntity {
    private static final long serialVersionUID = -1700649531350257386L;

    @Column(name = "KEY_")
    private String key;

    @Column(name = "KEY_LOCAL")
    private String keyLocal;

    @Column(name = "TEXT")
    private String text;

    public String getKeyLocal() {
        return keyLocal;
    }

    public void setKeyLocal(String keyLocal) {
        this.keyLocal = keyLocal;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }
}