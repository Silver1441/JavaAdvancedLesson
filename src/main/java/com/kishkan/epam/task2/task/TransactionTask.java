package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.repository.AccountsRepository;
import com.kishkan.epam.task2.service.TransactionManager;
import com.kishkan.epam.task2.utility.TransactionCounter;

import java.util.concurrent.ThreadLocalRandom;

public class TransactionTask implements Runnable {
    private final int minAmount = 1;
    private final int maxAmount = 30;

    private TransactionManager transactionManager;
    private AccountsRepository accountsRepository;

    public TransactionTask(TransactionManager transactionManager, AccountsRepository accountsRepository) {
        this.transactionManager = transactionManager;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void run() {
        while (TransactionCounter.getCounter() <= 1000) {
            Account sender = pickRandomAccount();
            Account recipient = pickRandomAccount();
            transactionManager.makeTransaction(sender, recipient, pickRandomValue());
        }
    }

    private Account pickRandomAccount() {
        int randomValue = ThreadLocalRandom.current().nextInt(accountsRepository.getAccountsNumber());
        return accountsRepository.getAccountByListIndex(randomValue);
    }

    private long pickRandomValue() {
        return ThreadLocalRandom.current().nextInt( minAmount, maxAmount);
    }
}
