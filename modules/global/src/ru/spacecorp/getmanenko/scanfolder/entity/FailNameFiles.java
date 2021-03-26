package ru.spacecorp.getmanenko.scanfolder.entity;

import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "SCANFOLDER_FAIL_NAME_FILES")
@Entity(name = "scanfolder_FailNameFiles")
public class FailNameFiles extends StandardEntity {
    private static final long serialVersionUID = -5600024189724802188L;

    @Column(name = "NAME_FILE")
    private String nameFile;

    public String getNameFile() {
        return nameFile;
    }

    public void setNameFile(String nameFile) {
        this.nameFile = nameFile;
    }
}