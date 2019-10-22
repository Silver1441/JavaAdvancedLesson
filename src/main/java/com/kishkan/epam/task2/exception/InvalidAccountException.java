package com.kishkan.epam.task2.exception;

import com.kishkan.epam.task2.entity.Account;

public class InvalidAccountException extends Exception {
    public InvalidAccountException(Account account, String message) {
        super("Account: " + account.getId() + " " + message);
    }
}
