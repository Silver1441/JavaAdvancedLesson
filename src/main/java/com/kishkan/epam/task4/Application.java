package com.kishkan.epam.task4;

import com.kishkan.epam.task4.service.FileManager;
import com.kishkan.epam.task4.service.PhoneLinesParser;
import com.kishkan.epam.task4.service.PhoneNumberFileManager;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        FileManager fileManager = new PhoneNumberFileManager();
        PhoneLinesParser phoneLinesParser = new PhoneLinesParser();

        try {
            String input = fileManager.getStringFromFile();
            String result = phoneLinesParser.parsePhoneNumbers(input);
            fileManager.writeFileFromString(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
