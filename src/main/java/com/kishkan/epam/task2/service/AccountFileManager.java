package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import lombok.Getter;
import lombok.Setter;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AccountFileManager {
    @Getter @Setter private String source = "src/main/resources/accounts/input/";
    @Getter @Setter private String prefix = "input_id";
    private final String EXTENSION = ".dat";

    public void writeAccountById(Account account) {
        try (FileOutputStream fileOutputStream =
                     new FileOutputStream(source + prefix + account.getId() + EXTENSION);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeAccountsFromList(List<Account> accounts) {
        for (Account account : accounts) {
            writeAccountById(account);
        }
    }

    public Account getAccountById(long id) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(source + prefix + id + EXTENSION);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Account) objectInputStream.readObject();
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> result = new ArrayList<>();
        try {
            scanFilePaths(result);
        } catch (IOException e) {
            e.printStackTrace();
        }
        return result;
    }

    private void scanFilePaths(List<Account> result) throws IOException {
        try (Stream<Path> walk = Files.walk(Paths.get(source))) {
            walk
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(path -> addAccountToListByPath(path, result));
        }
    }

    private void addAccountToListByPath(Path path, List<Account> result) {
        try (FileInputStream fileInputStream = new FileInputStream(path.toString());
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            result.add((Account) objectInputStream.readObject());
        } catch (ClassNotFoundException | IOException e) {
            e.printStackTrace();
        }
    }
}
