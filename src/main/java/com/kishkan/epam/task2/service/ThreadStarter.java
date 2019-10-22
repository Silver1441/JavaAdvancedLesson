package com.kishkan.epam.task2.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.kishkan.epam.task2.task.TransactionTask;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;

public class ThreadStarter {
    private int numberOfThreads = 20;
    private TransactionExecutor transactionExecutor;

    public ThreadStarter(TransactionExecutor transactionExecutor) {
        this.transactionExecutor = transactionExecutor;
    }

    public void startTransactionThreads() {
        final ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("Transactor-%d")
                .build();
        final ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads, factory);
        for (int i = 1; i <= numberOfThreads; i++) {
            executor.execute(new TransactionTask(transactionExecutor));
        }
        executor.shutdown();
    }
}
