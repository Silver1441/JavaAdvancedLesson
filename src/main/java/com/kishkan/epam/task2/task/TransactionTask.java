package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.service.TransactionManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class TransactionTask implements Runnable {
    @NonNull private TransactionManager transactionManager;

    @Override
    public void run() {
        transactionManager.startRandomTransaction();
    }
}
