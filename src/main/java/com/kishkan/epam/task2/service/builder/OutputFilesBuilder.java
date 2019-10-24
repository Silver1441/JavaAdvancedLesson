package com.kishkan.epam.task2.service.builder;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.kishkan.epam.task2.dao.AccountsDao;
import com.kishkan.epam.task2.dao.AccountsDaoImpl;
import com.kishkan.epam.task2.service.AccountFileManager;
import com.kishkan.epam.task2.service.TransactionManager;
import com.kishkan.epam.task2.task.TransactionTask;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;

@Slf4j
@RequiredArgsConstructor
public class OutputFilesBuilder {
    @Getter @Setter private int numberOfThreads = 20;
    @Getter @Setter private int numberOfTransactions = 1000;
    @NonNull private AccountFileManager accountFileManager;
    @NonNull private TransactionManager transactionManager;
    @NonNull private AccountsDao accountsDao;

    public void startTransactionThreads() {
        final ThreadFactory factory = new ThreadFactoryBuilder()
                .setNameFormat("Transactor-%d")
                .build();
        final ExecutorService executor = Executors.newFixedThreadPool(numberOfThreads, factory);
        checkSumFromFiles();
        for (int i = 1; i <= numberOfTransactions; i++) {
            executor.execute(new TransactionTask(transactionManager));
        }
        executor.shutdown();
        try {
            executor.awaitTermination(60, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            executor.shutdownNow();
        }
        writeResultFiles();
        checkSumFromFiles();
    }

    private void checkSumFromFiles() {
        AccountsDao temporalAccountsDao = new AccountsDaoImpl();
        temporalAccountsDao.setAccountsList(accountFileManager.getAllAccounts());
        long checkSum = temporalAccountsDao.getAccountsTotalBalance();
        log.info("Check sum: {} (thread: {})", checkSum, Thread.currentThread().getName());
    }

    private void writeResultFiles() {
        String outputSource = "src/main/resources/accounts/output/";
        String outputPrefix = "output_id";
        accountFileManager.setSource(outputSource);
        accountFileManager.setPrefix(outputPrefix);
        accountFileManager.writeAccountsFromList(accountsDao.getAccountsList());
    }
}
