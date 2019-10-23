package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.dao.AccountsDao;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
@RequiredArgsConstructor
public class TransactionManager {
    @Setter @Getter private int minAmount = 1;
    @Setter @Getter private int maxAmount = 30;

    @NonNull private AccountsDao accountsDao;
    @NonNull private TransactionExecutor transactionExecutor;

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
}
