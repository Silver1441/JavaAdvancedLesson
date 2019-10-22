package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import com.kishkan.epam.task2.utility.TransactionCounter;
import lombok.extern.slf4j.Slf4j;

//import static com.kishkan.epam.task2.utility.TransactionCounter.*;

@Slf4j
public class TransactionManager {
    public void makeTransaction(Account sender, Account recipient, long amount) {
        lockAccountsByIdPriority(sender, recipient);
        try {
            if (validateTransaction(sender, recipient, amount)) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
                int transactionId = TransactionCounter.incrementCounter();
                log.info("Transaction " + transactionId  + " " + amount + "$ - from: id" + sender.getId() + " to id" + recipient.getId() + ";");
            }
        } catch (InvalidAccountException e) {
            log.info("->Exception! Same id: {}", sender.getId());
        } catch (LowBalanceException e) {
            log.info("->Exception! Low balance, account id: {}", sender.getId());
        } finally {
            sender.unlockAccount();
            recipient.unlockAccount();
        }
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
//        if (TransactionCounter.getCounter() > 1000) {
//            throw new InvalidAccountException();
//        }
        return true;
    }
}
