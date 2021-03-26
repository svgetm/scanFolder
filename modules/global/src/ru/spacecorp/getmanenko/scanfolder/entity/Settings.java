package ru.spacecorp.getmanenko.scanfolder.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;
import java.util.List;

@Table(name = "SCANFOLDER_SETTINGS")
@Entity(name = "scanfolder_Settings")
@PublishEntityChangedEvents
@NamePattern("%s|name")

public class Settings extends StandardEntity {
    private static final long serialVersionUID = -8888091124970371738L;

    @Column(name = "NAME")
    @NotNull
    private String name;

    @Column(name = "FOLEDER_PATH")
    @NotNull
    protected String folederPath;

    @Column(name = "WORK_")
    @NotNull
    private Boolean work;

    @NotNull
    @Column(name = "SCANING", nullable = false)
    private Boolean scaning = false;

    @JoinTable(name = "SCANFOLDER_SETTINGS_EXPANSION_SETTINGS_LINK",
            joinColumns = @JoinColumn(name = "SETTINGS_ID"),
            inverseJoinColumns = @JoinColumn(name = "EXPANSION_SETTINGS_ID"))
    @ManyToMany
    private List<ExpansionSettings> expansion;

    @JoinTable(name = "SCANFOLDER_SETTINGS_FAIL_NAME_FILES_LINK",
            joinColumns = @JoinColumn(name = "SETTINGS_ID"),
            inverseJoinColumns = @JoinColumn(name = "FAIL_NAME_FILES_ID"))
    @ManyToMany
    private List<FailNameFiles> ignoreFiles;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "LAST_TIME_UPDATE")
    @NotNull
    protected Date lastTimeUpdate;

    public Boolean getScaning() {
        return scaning;
    }

    public void setScaning(Boolean scaning) {
        this.scaning = scaning;
    }

    public Boolean getWork() {
        return work;
    }

    public void setWork(Boolean work) {
        this.work = work;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<FailNameFiles> getIgnoreFiles() {
        return ignoreFiles;
    }

    public void setIgnoreFiles(List<FailNameFiles> ignoreFiles) {
        this.ignoreFiles = ignoreFiles;
    }

    public List<ExpansionSettings> getExpansion() {
        return expansion;
    }

    public void setExpansion(List<ExpansionSettings> expansion) {
        this.expansion = expansion;
    }

    public void setLastTimeUpdate(Date lastTimeUpdate) {
        this.lastTimeUpdate = lastTimeUpdate;
    }

    public Date getLastTimeUpdate() {
        return lastTimeUpdate;
    }

    public String getFolederPath() {
        return folederPath;
    }

    public void setFolederPath(String folederPath) {
        this.folederPath = folederPath;
    }

    @PostConstruct
    private void initSettingsActive() {
        if (work == null) {
            setWork(false);
        }
        if (scaning == null) {
            setScaning(false);
        }
    }
}
