package com.kishkan.epam.task1;

import com.kishkan.epam.task1.dto.HotelRequest;
import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.repository.HotelRequestRepositoryImpl;
import com.kishkan.epam.task1.service.HotelRequestBuffer;
import com.kishkan.epam.task1.service.ThreadsStarter;

import java.time.LocalDate;
import java.util.logging.Logger;

public class Application {
    private static Logger log = Logger.getLogger(Application.class.getName());
    public static void main(String[] args) {
        log.info("application init");
        final int NUMBER_OF_PRODUCERS = 3;
        final int NUMBER_OF_CONSUMERS = 6;
        HotelRequestBuffer hotelRequestBuffer = new HotelRequestBuffer();
        HotelRequestRepository hotelRequestRepository = new HotelRequestRepositoryImpl();
        ThreadsStarter threadsStarter = new ThreadsStarter(hotelRequestBuffer, hotelRequestRepository);
        new Application().fillRepositoryWithMocks(hotelRequestRepository);
        threadsStarter.startProducerThreads(NUMBER_OF_PRODUCERS);
        threadsStarter.startConsumerThreads(NUMBER_OF_CONSUMERS);
    }

    private void fillRepositoryWithMocks(HotelRequestRepository hotelRequestRepository) {
        hotelRequestRepository.addHotelRequest(new HotelRequest(1, LocalDate.now(), "NewLine"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(2, LocalDate.now(), "Walls"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(3, LocalDate.now(), "Pragma"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(4, LocalDate.now(), "NY"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(5, LocalDate.now(), "One for many"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(6, LocalDate.now(), "Family"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(7, LocalDate.now(), "Fun spot"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(8, LocalDate.now(), "Pilgrim"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(9, LocalDate.now(), "Lame horse"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(10, LocalDate.now(), "Natalie"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(11, LocalDate.now(), "Good place"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(12, LocalDate.now(), "Buy the garage"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(13, LocalDate.now(), "20$ per night"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(14, LocalDate.now(), "Cinema hostel"));
        hotelRequestRepository.addHotelRequest(new HotelRequest(15, LocalDate.now(), "Lux"));
    }

}
