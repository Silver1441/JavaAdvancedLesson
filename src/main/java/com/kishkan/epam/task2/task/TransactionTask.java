package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.repository.AccountsRepository;
import com.kishkan.epam.task2.service.TransactionManager;

import java.util.concurrent.ThreadLocalRandom;

public class TransactionTask implements Runnable {
    private TransactionManager transactionManager;
    private AccountsRepository accountsRepository;

    public TransactionTask(TransactionManager transactionManager, AccountsRepository accountsRepository) {
        this.transactionManager = transactionManager;
        this.accountsRepository = accountsRepository;
    }

    @Override
    public void run() {
        for(int i = 0; i < 5; i++) {
            Account sender = pickRandomAccount();
            Account recipient = pickRandomAccount();
//            Account sender = accountsRepository.getAccountById(1001L);
//            Account recipient = accountsRepository.getAccountById(1002l);
            transactionManager.makeTransaction(sender, recipient, pickRandomValue());
        }
    }

    private Account pickRandomAccount() {
        int randomValue = ThreadLocalRandom.current().nextInt(accountsRepository.getAccountsNumber());
        return accountsRepository.getAccountByListIndex(randomValue);
    }

    private long pickRandomValue() {
        return ThreadLocalRandom.current().nextInt(1, 30);
    }
}
