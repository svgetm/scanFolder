package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.entity.FileDescriptor;
import com.haulmont.cuba.core.global.*;
import org.apache.commons.io.FilenameUtils;
import org.springframework.stereotype.Component;
import ru.spacecorp.getmanenko.scanfolder.entity.ListAttributes;

import javax.inject.Inject;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.attribute.BasicFileAttributes;
import java.util.Date;

@Component(DbUpdateWorker.NAME)
public class DbUpdateWorker {
    public static final String NAME = "scanfolder_DbUpdateWorker";
    @Inject
    private Metadata metadata;
    @Inject
    private DataManager dataManager;


    public void addEntityBD(File file, Path path) throws IOException {
        ListAttributes listAttributes = metadata.create(ListAttributes.class);
        FileDescriptor fileDescriptor = metadata.create(FileDescriptor.class);
        CommitContext commitContext = new CommitContext(listAttributes, fileDescriptor);

        BasicFileAttributes attr = Files.readAttributes(path, BasicFileAttributes.class);

        addInfoFD(fileDescriptor, file, attr);
        addInfoInEntity(fileDescriptor, listAttributes, file.getAbsolutePath(),file);
        listAttributes.setFileDesc(fileDescriptor);

        dataManager.commit(commitContext);
    }

    public LoadContext<ListAttributes> contLoad(File file){
        LoadContext<ListAttributes> context = LoadContext.create(ListAttributes.class);
        context.setQuery(LoadContext.createQuery("select e from scanfolder_ListAttributes e where e.name = :name")
                .setParameter("name", file.getName()));
        return context;
    }


    private void addInfoFD(FileDescriptor fd, File file,BasicFileAttributes attributes) {
        fd.setName(file.getName());
        fd.setExtension(FilenameUtils.getExtension(file.getName()));
        fd.setSize(file.length());
        fd.setCreateDate(new Date(attributes.creationTime().toMillis()));

    }

    private void addInfoInEntity(FileDescriptor fileDesc, ListAttributes entity, String dir, File file) {
        entity.setName(fileDesc.getName());
        entity.setDataMod(new Date(file.lastModified()));
        entity.setExtension(fileDesc.getExtension());
        entity.setSize(fileDesc.getSize());
        entity.setDataCreate(fileDesc.getCreateDate());
        entity.setLinkDownload(String.valueOf(dir));

    }
}