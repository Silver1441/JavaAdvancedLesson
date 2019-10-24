package com.kishkan.epam.task2.dao;

import com.kishkan.epam.task2.entity.Account;

import java.util.List;

public interface AccountsDao {
    void addAccount(Account account);
    Account getAccountById(long id);
    Account getAccountByListIndex(int index);
    int getAccountsNumber();
    List<Account> getAccountsList();
    long getAccountsTotalBalance();
    void setAccountsList(List<Account> accounts);


}
