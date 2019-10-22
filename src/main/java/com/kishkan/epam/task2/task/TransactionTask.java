package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.service.TransactionExecutor;

public class TransactionTask implements Runnable {
    private TransactionExecutor transactionExecutor;

    public TransactionTask(TransactionExecutor transactionExecutor) {
        this.transactionExecutor = transactionExecutor;
    }

    @Override
    public void run() {
        for (int i = 0; i < 50; i++) {
            transactionExecutor.makeTransaction();
        }
    }
}
