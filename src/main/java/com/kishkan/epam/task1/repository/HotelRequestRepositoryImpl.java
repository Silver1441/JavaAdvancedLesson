package com.kishkan.epam.task1.repository;

import com.kishkan.epam.task1.dto.HotelRequest;

import java.util.ArrayDeque;
import java.util.Deque;

public class HotelRequestRepositoryImpl implements HotelRequestRepository {
    private Deque<HotelRequest> hotelRequests = new ArrayDeque<>();

    public boolean addHotelRequest(HotelRequest hotelRequest) {
        if (hotelRequest != null) {
            hotelRequests.addFirst(hotelRequest);
            return true;
        }
        return false;
    }

    public boolean isRequestStackEmpty() {
        return hotelRequests.isEmpty();
    }

    public HotelRequest getHotelRequest() {
        return hotelRequests.pollLast();
    }
}
