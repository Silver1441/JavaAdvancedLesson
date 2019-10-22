package com.kishkan.epam.task2.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.kishkan.epam.task2.dao.AccountsDao;
import com.kishkan.epam.task2.task.TransactionTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
public class ThreadStarter {
    private int numberOfThreads = 20;
    private TransactionManager transactionManager;
    private AccountsDao accountsDao;

    public ThreadStarter(TransactionManager transactionManager, AccountsDao accountsDao) {
        this.transactionManager = transactionManager;
        this.accountsDao = accountsDao;
    }

    public void startTransactionThreads() {
        final ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("Transactor-%d")
                .build();
        final ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads, factory);
        log.info("Tasks initialization, check sum: {} (thread: {})",
                accountsDao.getAccountsTotalBalance(), Thread.currentThread().getName());
        for (int i = 1; i <= numberOfThreads; i++) {
            executor.execute(new TransactionTask(transactionManager));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        log.info("All tasks are complete, check sum: {} (thread: {})",
                accountsDao.getAccountsTotalBalance(), Thread.currentThread().getName());
    }

    public int getNumberOfThreads() {
        return numberOfThreads;
    }

    public void setNumberOfThreads(int numberOfThreads) {
        this.numberOfThreads = numberOfThreads;
    }
}
