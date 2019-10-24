package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.utility.TransactionCounter;
import lombok.extern.slf4j.Slf4j;


@Slf4j
public class TransactionExecutor {

    public void makeTransaction(Account sender, Account recipient, long amount)
            throws InvalidAccountException, LowBalanceException {
        lockAccountsByIdPriority(sender, recipient);
        try {
            if (validateTransaction(sender, recipient, amount)) {
                int transactionId = TransactionCounter.incrementCounter();

                log.info("/ Transaction #{}: from id({}) to id({}) - {}$ (thread: {})",
                        transactionId, sender.getId(), recipient.getId(), amount, Thread.currentThread().getName());
                log.info("  Transaction #{}, before: from [id({}), balance({})] to [id({}), balance({})] (thread: {})",
                        transactionId, sender.getId(), sender.getBalance(), recipient.getId(), recipient.getBalance(),
                        Thread.currentThread().getName());

                makeCalculation(sender, recipient, amount);

                log.info("\\ Transaction #{}, after:  from [id({}), balance({})] to [id({}), balance({})] (thread: {})",
                        transactionId, sender.getId(), sender.getBalance(), recipient.getId(), recipient.getBalance(),
                        Thread.currentThread().getName());
            }
        } finally {
            unlockAccounts(sender, recipient);
        }
    }

    private void lockAccountsByIdPriority(Account sender, Account recipient) {
        if (sender.getId() > recipient.getId()) {
            sender.lockAccount();
            recipient.lockAccount();
        } else {
            recipient.lockAccount();
            sender.lockAccount();
        }
    }

    private boolean validateTransaction(Account sender, Account recipient, long amount)
            throws InvalidAccountException, LowBalanceException {
        if (sender.getBalance() < amount) {
            throw new LowBalanceException(sender);
        }

        if (sender.getId() == recipient.getId()) {
            throw new InvalidAccountException(sender);
        }
        return true;
    }

    private void makeCalculation(Account sender, Account recipient, long amount) {
        sender.setBalance(sender.getBalance() - amount);
        recipient.setBalance(recipient.getBalance() + amount);
    }

    private void unlockAccounts(Account sender, Account recipient) {
        sender.unlockAccount();
        recipient.unlockAccount();
    }

}
