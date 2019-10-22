package com.kishkan.epam.task2.task;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.repository.AccountsRepository;
import com.kishkan.epam.task2.service.TransactionManager;

public class SingleTransactionTask implements Runnable {
    TransactionManager transactionManager;
    Account targetAccount;

    public SingleTransactionTask(long targetAccountId,
                                 AccountsRepository accountsRepository, TransactionManager transactionManager) {
        this.targetAccount = accountsRepository.getAccountById(targetAccountId);
        this.transactionManager = transactionManager;
    }

    @Override
    public void run() {

    }

    private void sendingTransaction() {
        //transactionManager.makeTransaction(targetAccount);
    }

    private void receivingTransaction() {

    }
}
