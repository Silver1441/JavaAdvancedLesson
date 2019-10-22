package com.kishkan.epam.task2.repository;

import com.kishkan.epam.task2.entity.Account;

import java.util.List;

public interface AccountsRepository {
    void addAccount(Account account);
    Account getAccountById(long id);
    Account getAccountByListIndex(int index);
    int getAccountsNumber();
    List<Account> getAccountsList();
    void setAccountsList(List<Account> accounts);


}
