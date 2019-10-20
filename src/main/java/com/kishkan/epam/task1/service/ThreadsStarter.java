package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.runner.ConsumerTask;
import com.kishkan.epam.task1.runner.ProducerTask;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ThreadFactory;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

@Slf4j
public class ThreadsStarter {
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
        log.info("Producer executor service was shut down;");
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
        log.info("Consumer executor service was shut down;");
        return true;
    }
}
