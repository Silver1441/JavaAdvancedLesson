package com.kishkan.epam.task2.utility;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class TransactionCounter {
    private static Lock lock = new ReentrantLock();
    private static AtomicInteger counter = new AtomicInteger(0);

    public static int incrementCounter() {
        return counter.incrementAndGet();
    }

    public static int getCounter() {
        return counter.get();
    }

    public static void lockCounter() {
        lock.lock();
    }

    public static void unlockCounter() {
        lock.unlock();
    }
}
