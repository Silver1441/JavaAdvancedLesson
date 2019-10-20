package com.kishkan.epam.task2;


import com.kishkan.epam.task2.service.AccountFileManager;

import java.io.IOException;

public class Application {

    public static void main(String[] args) {
        AccountFileManager accountFileManager = new AccountFileManager();


        try {
            String testToWrite = "test\n test";
            accountFileManager.updateFileById(1002L, testToWrite);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            String result = accountFileManager.getFileById(1002L);
            System.out.println(result);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}
