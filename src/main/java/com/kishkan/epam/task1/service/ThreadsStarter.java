package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.runner.ConsumerTask;
import com.kishkan.epam.task1.runner.ProducerTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.TimeUnit;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

public class ThreadsStarter {
    private static Logger log = LoggerFactory.getLogger(ThreadsStarter.class);
    private HotelRequestBuffer hotelRequestBuffer;
    private HotelRequestRepository hotelRequestRepository;

    public ThreadsStarter(HotelRequestBuffer hotelRequestBuffer, HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestBuffer = hotelRequestBuffer;
        this.hotelRequestRepository = hotelRequestRepository;
    }

    public boolean startProducerThreads(int numberOfThreads) {
        final ThreadFactory producerFactory = new ThreadFactoryBuilder()
                .setNameFormat("Producer-%d")
                .build();
        final ExecutorService producerExecutor = Executors.newFixedThreadPool(numberOfThreads, producerFactory);

        for (int i = 1; i <= numberOfThreads; i++) {
            producerExecutor.execute(new ProducerTask(hotelRequestBuffer, hotelRequestRepository));
        }
        producerExecutor.shutdown();

//        try {
//            producerExecutor.awaitTermination(10, TimeUnit.SECONDS);
//        } catch (InterruptedException e) {
//            producerExecutor.shutdownNow();
//        }
        log.info("  Producer executor service was shut down;");
        return true;
    }

    public boolean startConsumerThreads(int numberOfThreads) {
        final ThreadFactory consumerFactory = new ThreadFactoryBuilder()
                .setNameFormat("Consumer-%d")
                .build();
        final ExecutorService consumerExecutor = Executors.newFixedThreadPool(numberOfThreads, consumerFactory);

        for (int i = 1; i <= numberOfThreads; i++) {
            consumerExecutor.execute(new ConsumerTask(hotelRequestBuffer, hotelRequestRepository));
        }
        consumerExecutor.shutdown();

        try {
            consumerExecutor.awaitTermination(10, TimeUnit.SECONDS);
        } catch (InterruptedException e) {
            consumerExecutor.shutdownNow();
        }
        log.info("  Consumer executor service was shut down;");
        return true;
    }
}
