package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.service.TransactionManager;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionTask implements Runnable {
    private TransactionManager transactionManager;

    public TransactionTask(TransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }

    @Override
    public void run() {
        log.info("{} starts it's work", Thread.currentThread().getName());
        for (int i = 0; i < 50; i++) {
            transactionManager.startRandomTransaction();
        }
        log.info("{} ends it's work", Thread.currentThread().getName());
    }
}
