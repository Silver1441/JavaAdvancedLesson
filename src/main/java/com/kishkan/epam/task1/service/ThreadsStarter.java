package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.runner.ConsumerTask;
import com.kishkan.epam.task1.runner.ProducerTask;

import java.util.logging.Logger;

public class ThreadsStarter {
    private static Logger log = Logger.getLogger( ThreadsStarter.class.getName());
    private HotelRequestBuffer hotelRequestBuffer;
    private HotelRequestRepository hotelRequestRepository;

    public ThreadsStarter(HotelRequestBuffer hotelRequestBuffer, HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestBuffer = hotelRequestBuffer;
        this.hotelRequestRepository = hotelRequestRepository;
    }

    public boolean startProducerThreads(int numberOfThreads) {
        for (int i = 1; i <= numberOfThreads; i++) {
            Thread producer = new Thread(new ProducerTask(hotelRequestBuffer, hotelRequestRepository));
            producer.setName("Producer #" + i);
            producer.start();
            log.info(producer.getName() + " is working now;");
        }
        return true;
    }

    public boolean startConsumerThreads(int numberOfThreads) {
        for (int i = 1; i <= numberOfThreads; i++) {
            Thread consumer = new Thread(new ConsumerTask(hotelRequestBuffer, hotelRequestRepository));
            consumer.setName("Consumer #" + i);
            consumer.start();
            log.info(consumer.getName() + " is working now;");
        }
        return true;
    }
}
