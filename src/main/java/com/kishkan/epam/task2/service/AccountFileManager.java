package com.kishkan.epam.task2.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class AccountFileManager {
    private String source = "src/main/resources/accounts/";
    private final String EXTENSION = ".dat";
    private final String PREFIX = "id";

    public AccountFileManager() {
    }

    public AccountFileManager(String accountSource) {
        this.source = accountSource;
    }

    public String getFileById(Long id) throws IOException {
        Path path = Paths.get(source + PREFIX + id + EXTENSION);
        return new String(Files.readAllBytes(path));
    }

    public void updateFileById(Long id, String string) throws IOException {
        Path path = Paths.get(source + PREFIX + id + EXTENSION);
        Files.write(path, string.getBytes());
    }








    public String getSource() {
        return source;
    }

    public void changeSource(String source) {
        this.source = source;
    }
}
