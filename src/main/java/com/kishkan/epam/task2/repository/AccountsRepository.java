package com.kishkan.epam.task2.repository;

import com.kishkan.epam.task2.dto.Account;

public interface AccountsRepository {
    void addAccount(Account account);
    Account getAccountById(long id);
}
