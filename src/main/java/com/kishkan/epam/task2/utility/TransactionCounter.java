package com.kishkan.epam.task2.utility;

import java.util.concurrent.atomic.AtomicInteger;

public class TransactionCounter {
    private static AtomicInteger counter = new AtomicInteger(0);

    public static int incrementCounter() {
        return counter.incrementAndGet();
    }
}
