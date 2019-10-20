package com.kishkan.epam.task2.repository;

import com.kishkan.epam.task2.dto.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountsRepositoryImpl implements AccountsRepository {
    private List<Account> accounts = new ArrayList<>();

    public void addAccount(Account account) {
        accounts.add(account);
    }

    public Account getAccountById(long id) {
        return accounts.stream()
                .filter((account)->(account.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("id: " + id + "is not found;"));
    }
}
