package com.kishkan.epam.task2;


import com.kishkan.epam.task2.dto.Account;
import com.kishkan.epam.task2.repository.AccountsRepositoryImpl;
import com.kishkan.epam.task2.service.AccountFileManager;

import java.io.IOException;
import java.util.List;

public class Application {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        AccountFileManager accountFileManager = new AccountFileManager();
//        Account account = new Account("Mike", "Smith", 1002L, 685);
//
//        accountFileManager.writeAccountById(account);
        Account resultAcc = accountFileManager.getAccountById(1002L);
        System.out.println(resultAcc);

        List<Account> result = accountFileManager.getAllAccounts();
        System.out.println(result);
    }
}
