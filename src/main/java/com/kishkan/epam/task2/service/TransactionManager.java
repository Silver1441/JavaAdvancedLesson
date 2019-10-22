package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.dao.AccountsDao;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TransactionManager {
    private int minAmount = 1;
    private int maxAmount = 30;

    private AccountsDao accountsDao;
    private TransactionExecutor transactionExecutor;

    public TransactionManager(AccountsDao accountsDao, TransactionExecutor transactionExecutor) {
        this.accountsDao = accountsDao;
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
        int randomValue = ThreadLocalRandom.current().nextInt(accountsDao.getAccountsNumber());
        return accountsDao.getAccountByListIndex(randomValue);
    }

    private long pickRandomValue() {
        return ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
    }

    public int getMinAmount() {
        return minAmount;
    }

    public void setMinAmount(int minAmount) {
        this.minAmount = minAmount;
    }

    public int getMaxAmount() {
        return maxAmount;
    }

    public void setMaxAmount(int maxAmount) {
        this.maxAmount = maxAmount;
    }
}
