package com.kishkan.epam.task2.service;

import com.kishkan.epam.task2.entity.Account;
import com.kishkan.epam.task2.exception.InvalidAccountException;
import com.kishkan.epam.task2.exception.LowBalanceException;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class TransactionManager {
    public void makeTransaction(Account sender, Account recipient, long amount) {
        log.info("Transaction " + amount + "$ - from: id" + sender.getId() + " to id" + recipient.getId() + ";");
        lockAccountsByIdPriority(sender, recipient);
        try {
            if (validateTransaction(sender, recipient, amount)) {
                sender.setBalance(sender.getBalance() - amount);
                recipient.setBalance(recipient.getBalance() + amount);
            }
        } catch (InvalidAccountException e) {
            log.info("->!Same id: {}", sender.getId());
        } catch (LowBalanceException e) {
            log.info("->!Low balance");
        } finally {
            sender.unlockAccount();
            recipient.unlockAccount();
        }
    }

    private void lockAccountsByIdPriority(Account sender, Account recipient) {
        long senderId = sender.getId();
        long recipientId = recipient.getId();
        if (senderId > recipientId) {
            sender.lockAccount();
            recipient.lockAccount();
        } else {
            recipient.lockAccount();
            sender.lockAccount();
        }
    }

    private boolean validateTransaction(Account sender, Account recipient, long amount) throws InvalidAccountException, LowBalanceException {
        if (sender.getBalance() < amount) {

            //return false;
            throw new LowBalanceException(sender);
        }

        if (sender.getId() == recipient.getId()) {
            //return false;
            throw new InvalidAccountException(sender, "transaction to the same account!");
        }
        return true;
    }
}
