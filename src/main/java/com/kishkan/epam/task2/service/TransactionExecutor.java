package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.repository.AccountsRepository;
import com.kishkan.epam.task2.utility.TransactionCounter;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ThreadLocalRandom;

@Slf4j
public class TransactionExecutor {
    private final int minAmount = 1;
    private final int maxAmount = 30;

    private AccountsRepository accountsRepository;

    public TransactionExecutor(AccountsRepository accountsRepository) {
        this.accountsRepository = accountsRepository;
    }

    public void makeTransaction() {
        Account sender = pickRandomAccount();
        Account recipient = pickRandomAccount();
        long amount = pickRandomValue();

        lockAccountsByIdPriority(sender, recipient);
        try {
            if (validateTransaction(sender, recipient, amount)) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                int transactionId = TransactionCounter.incrementCounter();
                log.info("Transaction " + transactionId  + " " + amount + "$ - from: id" + sender.getId() + " to id" + recipient.getId() + ";");
                unlockAccounts(sender, recipient);
            }
        } catch (InvalidAccountException e) {
            log.info("->Exception! Same id: {}", sender.getId());
            unlockAccounts(sender, recipient);
            makeTransaction();
        } catch (LowBalanceException e) {
            log.info("->Exception! Low balance, account id: {}", sender.getId());
            unlockAccounts(sender, recipient);
            makeTransaction();
        }
    }

    private Account pickRandomAccount() {
        int randomValue = ThreadLocalRandom.current().nextInt(accountsRepository.getAccountsNumber());
        return accountsRepository.getAccountByListIndex(randomValue);
    }

    private long pickRandomValue() {
        return ThreadLocalRandom.current().nextInt(minAmount, maxAmount);
    }

    private void lockAccountsByIdPriority(Account sender, Account recipient) {
        if (sender.getId() > recipient.getId()) {
            sender.lockAccount();
            recipient.lockAccount();
        } else {
            recipient.lockAccount();
            sender.lockAccount();
        }
    }

    private boolean validateTransaction(Account sender, Account recipient, long amount)
            throws InvalidAccountException, LowBalanceException {
        if (sender.getBalance() < amount) {
            throw new LowBalanceException();
        }

        if (sender.getId() == recipient.getId()) {
            throw new InvalidAccountException();
        }
        return true;
    }

    private void unlockAccounts(Account sender, Account recipient) {
        sender.unlockAccount();
        recipient.unlockAccount();
    }

}
