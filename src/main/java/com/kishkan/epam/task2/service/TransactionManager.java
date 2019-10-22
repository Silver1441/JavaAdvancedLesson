package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.repository.AccountsRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TransactionManager {
    private final int minAmount = 1;
    private final int maxAmount = 30;

    private AccountsRepository accountsRepository;
    private TransactionExecutor transactionExecutor;

    public TransactionManager(AccountsRepository accountsRepository, TransactionExecutor transactionExecutor) {
        this.accountsRepository = accountsRepository;
        this.transactionExecutor = transactionExecutor;
    }

    public void startRandomTransaction() {
        try {
            transactionExecutor.makeTransaction(pickRandomAccount(), pickRandomAccount(), pickRandomValue());
        } catch (InvalidAccountException e) {
            log.info("->Exception! Same id: {}", e.getAccountId());
            startRandomTransaction();
        } catch (LowBalanceException e) {
            log.info("->Exception! Low balance, account id: {}", e.getAccountId());
            startRandomTransaction();
        }
    }

    private Account pickRandomAccount() {
        int randomValue = ThreadLocalRandom.current().nextInt(accountsRepository.getAccountsNumber());
        return accountsRepository.getAccountByListIndex(randomValue);
    }

    private long pickRandomValue() {
        return ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
    }
}
