package com.kishkan.epam.task2.repository;

import com.kishkan.epam.task2.entity.Account;

import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

public class AccountsRepositoryImpl implements AccountsRepository {
    private List<Account> accounts = new ArrayList<>();

    @Override
    public void addAccount(Account account) {
        accounts.add(account);
    }

    @Override
    public synchronized Account getAccountById(long id) {
        return accounts.stream()
                .filter((account) -> (account.getId() == id))
                .findFirst()
                .orElseThrow(() -> new NoSuchElementException("id: " + id + "is not found;"));
    }

    @Override
    public synchronized Account getAccountByListIndex(int index) {
        return accounts.get(index);
    }

    @Override
    public synchronized int getAccountsNumber() {
        return accounts.size();
    }

    @Override
    public synchronized List<Account> getAccountsList() {
        return accounts;
    }

    @Override
    public void setAccountsList(List<Account> accounts) {
        this.accounts = accounts;
    }
}
