package com.kishkan.epam.task2;


import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.dao.AccountsDao;
import com.kishkan.epam.task2.dao.AccountsDaoImpl;
import com.kishkan.epam.task2.service.AccountFileManager;
import com.kishkan.epam.task2.service.ThreadStarter;
import com.kishkan.epam.task2.service.TransactionExecutor;
import com.kishkan.epam.task2.service.TransactionManager;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AccountFileManager accountFileManager = new AccountFileManager();
        AccountsDao accountsDao = new AccountsDaoImpl();
        TransactionExecutor transactionExecutor = new TransactionExecutor();
        TransactionManager transactionManager = new TransactionManager(accountsDao, transactionExecutor);
        Account account1 = new Account("John", "Doe", 1001L, 54655);
        accountFileManager.writeAccountById(account1);
        Account account2 = new Account("Mike", "Smith", 1002L, 685);
        accountFileManager.writeAccountById(account2);
        Account account3 = new Account("Bob", "Bobby", 1003L, 1425);
        accountFileManager.writeAccountById(account3);
//        Account resultAcc = accountFileManager.getAccountById(1002L);
//        System.out.println(resultAcc);
//
        List<Account> result = accountFileManager.getAllAccounts();
//        System.out.println(result);
        accountsDao.setAccountsList(result);

        ThreadStarter threadStarter = new ThreadStarter(transactionManager, accountsDao);
        threadStarter.startTransactionThreads();
    }
}
