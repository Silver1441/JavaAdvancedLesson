package com.kishkan.epam.task2.entity;

import lombok.*;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

@Slf4j
@ToString
@RequiredArgsConstructor
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;

    @Setter @Getter @NonNull private String name;
    @Setter @Getter @NonNull private String surname;
    @Getter @NonNull private final long id;
    @Setter @Getter @NonNull private long balance;

    @ToString.Exclude private Lock lock = new ReentrantLock();

    public void lockAccount() {
        if(lock.tryLock()) {
            log.info("!Account id({}) is locked by another transaction (thread: {})",
                    getId(), Thread.currentThread().getName());
        } else {
            lock.lock();
        }
    }
    public void unlockAccount() {
        lock.unlock();
    }
}
