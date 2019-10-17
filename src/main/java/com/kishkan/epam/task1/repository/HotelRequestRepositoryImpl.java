package com.kishkan.epam.task1.repository;

import com.kishkan.epam.task1.dto.HotelRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

public class HotelRequestRepositoryImpl implements HotelRequestRepository {
    private static Logger log = Logger.getLogger(HotelRequestRepository.class.getName());
    private Deque<HotelRequest> hotelRequests = new ArrayDeque<>();

    @Override
    public synchronized boolean addHotelRequest(HotelRequest hotelRequest) {
        if (hotelRequest != null) {
            hotelRequests.addFirst(hotelRequest);
            return true;
        }
        return false;
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
