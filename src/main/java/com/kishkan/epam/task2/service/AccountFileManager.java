package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.dto.Account;

import java.io.*;

public class AccountFileManager {
    private String source = "src/main/resources/accounts/";
    private final String EXTENSION = ".dat";
    private final String PREFIX = "id";

    public AccountFileManager() {
    }

    public AccountFileManager(String accountSource) {
        this.source = accountSource;
    }

    public void writeAccountById(Account account, long id) throws IOException {
        FileOutputStream fileOutputStream = new FileOutputStream(source + PREFIX + id + EXTENSION);
        ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream);
        objectOutputStream.writeObject(account);
    }

    public Account getAccountById(long id) throws IOException, ClassNotFoundException {
        FileInputStream fileInputStream = new FileInputStream(source + PREFIX + id + EXTENSION);
        ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream);

        return (Account) objectInputStream.readObject();
    }








    public String getSource() {
        return source;
    }

    public void changeSource(String source) {
        this.source = source;
    }
}
