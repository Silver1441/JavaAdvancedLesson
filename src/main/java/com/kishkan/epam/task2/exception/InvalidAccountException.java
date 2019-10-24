package com.kishkan.epam.task2.exception;

import com.kishkan.epam.task2.entity.Account;

public class InvalidAccountException extends Exception {
    private Account account;

    public InvalidAccountException(Account account) {
        this.account = account;
    }

    public long getAccountId() {
        return account.getId();
    }
}
