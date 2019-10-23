package com.kishkan.epam.task4.service;

import lombok.Getter;
import lombok.Setter;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

public class PhoneNumberFileManager implements FileManager {
    @Setter @Getter private String inputSource = "src/main/resources/";
    @Setter @Getter private String outputSource = "src/main/resources/";
    @Setter @Getter private String inputFileName = "phone_report.txt";
    @Setter @Getter private String outputFileName = "phones_list.txt";

    @Override
    public String getStringFromFile() throws IOException {
        return Files.readString(Paths.get(inputSource + inputFileName));
    }

    @Override
    public void writeFileFromString(String text) throws IOException {
        Files.write(Paths.get(outputSource + outputFileName), text.getBytes());
    }
}
