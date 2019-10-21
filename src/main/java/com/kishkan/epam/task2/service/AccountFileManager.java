package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.dto.Account;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Stream;

public class AccountFileManager {
    private String source = "src/main/resources/accounts/";
    private final String EXTENSION = ".dat";
    private final String PREFIX = "id";

    public void writeAccountById(Account account) {
        try (FileOutputStream fileOutputStream = new FileOutputStream(source + PREFIX + account.getId() + EXTENSION);
             ObjectOutputStream objectOutputStream = new ObjectOutputStream(fileOutputStream)) {
            objectOutputStream.writeObject(account);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Account getAccountById(long id) throws IOException, ClassNotFoundException {
        try (FileInputStream fileInputStream = new FileInputStream(source + PREFIX + id + EXTENSION);
             ObjectInputStream objectInputStream = new ObjectInputStream(fileInputStream)) {
            return (Account) objectInputStream.readObject();
        }
    }

    public List<Account> getAllAccounts() {
        List<Account> result = new ArrayList<>();
        scanFilePaths(result);
        return result;
    }

    private void scanFilePaths(List<Account> result) {
        try (Stream<Path> walk = Files.walk(Paths.get(source))) {
            walk
                    .filter(path -> Files.isRegularFile(path))
                    .forEach(path -> addAccountToListByPath(path, result));
        } catch (IOException e) {
            e.printStackTrace();
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

    public String getSource() {
        return source;
    }

    public void changeSource(String source) {
        this.source = source;
    }
}
