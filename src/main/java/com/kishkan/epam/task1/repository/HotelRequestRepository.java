package com.kishkan.epam.task1.repository;

import com.kishkan.epam.task1.dto.HotelRequest;

public interface HotelRequestRepository {
    void addHotelRequest(HotelRequest hotelRequest);
    boolean isRequestStackEmpty();
    HotelRequest getHotelRequest();
}
