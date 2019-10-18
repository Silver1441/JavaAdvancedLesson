package com.kishkan.epam.task1.repository;

import com.kishkan.epam.task1.dto.HotelRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

public class HotelRequestRepositoryMockImpl implements HotelRequestRepository {
    private static Logger log = LoggerFactory.getLogger(HotelRequestRepository.class);
    private Deque<HotelRequest> hotelRequests = new ArrayDeque<>();

    public HotelRequestRepositoryMockImpl() {
        addHotelRequest(new HotelRequest(1, LocalDate.now(), "NewLine"));
        addHotelRequest(new HotelRequest(2, LocalDate.now(), "Walls"));
        addHotelRequest(new HotelRequest(3, LocalDate.now(), "Pragma"));
        addHotelRequest(new HotelRequest(4, LocalDate.now(), "NY"));
        addHotelRequest(new HotelRequest(5, LocalDate.now(), "One for many"));
        addHotelRequest(new HotelRequest(6, LocalDate.now(), "Family"));
        addHotelRequest(new HotelRequest(7, LocalDate.now(), "Fun spot"));
        addHotelRequest(new HotelRequest(8, LocalDate.now(), "Pilgrim"));
        addHotelRequest(new HotelRequest(9, LocalDate.now(), "Lame horse"));
        addHotelRequest(new HotelRequest(10, LocalDate.now(), "Natalie"));
        addHotelRequest(new HotelRequest(11, LocalDate.now(), "Good place"));
        addHotelRequest(new HotelRequest(12, LocalDate.now(), "Buy the garage"));
        addHotelRequest(new HotelRequest(13, LocalDate.now(), "20$ per night"));
        addHotelRequest(new HotelRequest(14, LocalDate.now(), "Cinema hostel"));
        addHotelRequest(new HotelRequest(15, LocalDate.now(), "Lux"));
    }

    @Override
    public void addHotelRequest(HotelRequest hotelRequest) {
        hotelRequests.addFirst(hotelRequest);
    }

    @Override
    public synchronized boolean isRequestStackEmpty() {
        return hotelRequests.isEmpty();
    }

    @Override
    public synchronized HotelRequest getHotelRequest() {
        log.info("Current number of unproduced requests: "
                + hotelRequests.size() + ", " + Thread.currentThread().getName() + " is taking one now;");
        return hotelRequests.pollLast();
    }
}
