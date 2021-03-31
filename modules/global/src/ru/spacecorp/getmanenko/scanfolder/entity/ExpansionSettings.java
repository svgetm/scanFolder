package ru.spacecorp.getmanenko.scanfolder.entity;

import com.haulmont.chile.core.annotations.NamePattern;
import com.haulmont.cuba.core.entity.StandardEntity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Table(name = "SCANFOLDER_EXPANSION_SETTINGS")
@Entity(name = "scanfolder_ExpansionSettings")
@NamePattern("%s|expansion")
public class ExpansionSettings extends StandardEntity {
    private static final long serialVersionUID = -1424157849323948580L;

    @Column(name = "EXPANSION")
    private String expansion;

    public String getExpansion() {
        return expansion;
    }

    public void setExpansion(String expansion) {
        this.expansion = expansion;
    }
}