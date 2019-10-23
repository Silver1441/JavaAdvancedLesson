package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.service.TransactionManager;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class TransactionTask implements Runnable {
    @NonNull private TransactionManager transactionManager;
    @Setter @Getter private int transactionsNumberPerTask = 50;

    @Override
    public void run() {
        log.info("{} starts it's work", Thread.currentThread().getName());
        for (int i = 0; i < transactionsNumberPerTask; i++) {
            transactionManager.startRandomTransaction();
        }
        log.info("{} ends it's work", Thread.currentThread().getName());
    }
}
