package com.kishkan.epam.task1;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.repository.HotelRequestRepositoryMockImpl;
import com.kishkan.epam.task1.service.HotelRequestBuffer;
import com.kishkan.epam.task1.service.ThreadsStarter;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class Application {
    private static Logger log = LoggerFactory.getLogger(Application.class);
    public static void main(String[] args) {
        log.info("->Application init;");
        final int NUMBER_OF_PRODUCERS = 3;
        final int NUMBER_OF_CONSUMERS = 6;
        HotelRequestRepository hotelRequestRepository = new HotelRequestRepositoryMockImpl();
        HotelRequestBuffer hotelRequestBuffer = new HotelRequestBuffer(hotelRequestRepository);
        ThreadsStarter threadsStarter = new ThreadsStarter(hotelRequestBuffer, hotelRequestRepository);

        threadsStarter.startProducerThreads(NUMBER_OF_PRODUCERS);
        threadsStarter.startConsumerThreads(NUMBER_OF_CONSUMERS);
    }

}
