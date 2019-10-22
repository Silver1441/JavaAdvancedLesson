package com.kishkan.epam.task2.exception;

import com.kishkan.epam.task2.entity.Account;

public class LowBalanceException extends Exception {
    public LowBalanceException(Account account) {
        super("Account: " + account.getId() + ", the balance too low for transaction");
    }
}
