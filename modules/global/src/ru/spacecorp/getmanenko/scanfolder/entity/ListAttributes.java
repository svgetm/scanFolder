package ru.spacecorp.getmanenko.scanfolder.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.entity.StandardEntity;
import com.haulmont.cuba.core.entity.annotation.Listeners;
import com.haulmont.cuba.core.entity.annotation.PublishEntityChangedEvents;

import javax.persistence.*;
import java.util.Date;

@NamePattern("%s|name")
@Table(name = "SCANFOLDER_LIST_ATTRIBUTES")
@Entity(name = "scanfolder_ListAttributes")
@Listeners("scanfolder_HelperEvent")
@PublishEntityChangedEvents
public class ListAttributes extends StandardEntity {
    private static final long serialVersionUID = 7934465821227176173L;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "FILE_DESC_ID")
    protected FileDescriptor fileDesc;

    @Column(name = "NAME")
    protected String name;

    @Column(name = "EXTENSION")
    protected String extension;

    @Column(name = "SIZE_")
    protected Long size;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_CREATE")
    protected Date dataCreate;

    @Temporal(TemporalType.TIMESTAMP)
    @Column(name = "DATA_MOD")
    protected Date dataMod;

    @Column(name = "LINK_DOWNLOAD")
    protected String linkDownload;

    public void setLinkDownload(String linkDownload) {
        this.linkDownload = linkDownload;
    }

    public String getLinkDownload() {
        return linkDownload;
    }

    public Date getDataCreate() {
        return dataCreate;
    }

    public void setDataCreate(Date dataCreate) {
        this.dataCreate = dataCreate;
    }

    public Date getDataMod() {
        return dataMod;
    }

    public void setDataMod(Date dataMod) {
        this.dataMod = dataMod;
    }

    public Long getSize() {
        return size;
    }

    public void setSize(Long size) {
        this.size = size;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public FileDescriptor getFileDesc() {
        return fileDesc;
    }

    public void setFileDesc(FileDescriptor fileDesc) {
        this.fileDesc = fileDesc;
    }
}