package com.kishkan.epam.task2.exception;

import com.kishkan.epam.task2.entity.Account;

public class LowBalanceException extends Exception {
    private Account account;

    public LowBalanceException(Account account) {
        this.account = account;
    }

    public long getAccountId() {
        return account.getId();
    }
}
