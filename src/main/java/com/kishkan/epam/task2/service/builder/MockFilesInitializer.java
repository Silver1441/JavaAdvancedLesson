package com.kishkan.epam.task2.service.builder;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.service.AccountFileManager;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class MockFilesInitializer {
    @NonNull private AccountFileManager accountFileManager;

    public void writeFiles() {
        accountFileManager.writeAccountById(new Account("John", "Doe", 1001L, 54655));
        accountFileManager.writeAccountById(new Account("Mike", "Smith", 1002L, 685));
        accountFileManager.writeAccountById(new Account("Bob", "Bobby", 1003L, 1425));
        accountFileManager.writeAccountById(new Account("Nicolas", "Cage", 1004L, 4756));
        accountFileManager.writeAccountById(new Account("Simon", "Dur", 1005L, 45478));
        accountFileManager.writeAccountById(new Account("Ramile", "Hasan", 1006L, 8674));
        accountFileManager.writeAccountById(new Account("Olga", "Williams", 1007L, 2468));
        accountFileManager.writeAccountById(new Account("Jane", "Brown", 1008L, 14598));
        accountFileManager.writeAccountById(new Account("James", "Johnson", 1009L, 574));
        accountFileManager.writeAccountById(new Account("Richard", "Clark", 1010L, 7532));
    }


}
