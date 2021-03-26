package ru.spacecorp.getmanenko.scanfolder.core;

import com.haulmont.cuba.core.global.Messages;
import org.springframework.stereotype.Component;

import javax.inject.Inject;
import java.nio.file.Files;
import java.nio.file.Paths;


@Component(CheckerIsCorrectFolder.NAME)
public class CheckerIsCorrectFolder {
    public static final String NAME = "scanfolder_CheckerIsCorrectFolder";

    public boolean availabilityCheck(String folder) {
        return Files.exists(Paths.get(folder));

    }
}