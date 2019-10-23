package com.kishkan.epam.task2;

import com.kishkan.epam.task2.dao.AccountsDao;
import com.kishkan.epam.task2.dao.AccountsDaoImpl;
import com.kishkan.epam.task2.service.AccountFileManager;
import com.kishkan.epam.task2.service.builder.OutputFilesBuilder;
import com.kishkan.epam.task2.service.TransactionExecutor;
import com.kishkan.epam.task2.service.TransactionManager;
import com.kishkan.epam.task2.service.builder.MockFilesInitializer;

public class Application {

    public static void main(String[] args) {
        AccountFileManager accountFileManager = new AccountFileManager();
        AccountsDao accountsDao = new AccountsDaoImpl();
        MockFilesInitializer mockFilesInitializer = new MockFilesInitializer(accountFileManager);
        TransactionExecutor transactionExecutor = new TransactionExecutor();
        TransactionManager transactionManager = new TransactionManager(accountsDao, transactionExecutor);
        OutputFilesBuilder outputFilesBuilder = new OutputFilesBuilder(accountFileManager, transactionManager, accountsDao);

        mockFilesInitializer.writeFiles();
        accountsDao.setAccountsList(accountFileManager.getAllAccounts());
        outputFilesBuilder.startTransactionThreads();
    }
}
