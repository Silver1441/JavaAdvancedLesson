package com.kishkan.epam.task4.service;

import java.io.IOException;

public interface FileManager {
    String getStringFromFile() throws IOException;

    void writeFileFromString(String text) throws IOException;
}
