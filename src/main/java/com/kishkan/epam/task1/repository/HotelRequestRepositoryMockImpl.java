package com.kishkan.epam.task1.repository;

import com.kishkan.epam.task1.dto.HotelRequest;
import lombok.extern.slf4j.Slf4j;

import java.time.LocalDate;
import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
public class HotelRequestRepositoryMockImpl implements HotelRequestRepository {
    private Deque<HotelRequest> hotelRequests = new ArrayDeque<>();

    public HotelRequestRepositoryMockImpl() {
        addHotelRequest(new HotelRequest(1, "NewLine"));
        addHotelRequest(new HotelRequest(2, "Walls"));
        addHotelRequest(new HotelRequest(3, "Pragma"));
        addHotelRequest(new HotelRequest(4, "NY"));
        addHotelRequest(new HotelRequest(5, "One for many"));
        addHotelRequest(new HotelRequest(6, "Family"));
        addHotelRequest(new HotelRequest(7, "Fun spot"));
        addHotelRequest(new HotelRequest(8, "Pilgrim"));
        addHotelRequest(new HotelRequest(9, "Lame horse"));
        addHotelRequest(new HotelRequest(10, "Natalie"));
        addHotelRequest(new HotelRequest(11, "Good place"));
        addHotelRequest(new HotelRequest(12, "Buy the garage"));
        addHotelRequest(new HotelRequest(13, "20$ per night"));
        addHotelRequest(new HotelRequest(14, "Cinema hostel"));
        addHotelRequest(new HotelRequest(15, "Lux"));
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
        log.info("Current number of unproduced requests: {}, {} is taking one now;",
                hotelRequests.size(), Thread.currentThread().getName());
        return hotelRequests.pollLast();
    }
}
