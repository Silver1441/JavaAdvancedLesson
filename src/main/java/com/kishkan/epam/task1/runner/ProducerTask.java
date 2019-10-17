package com.kishkan.epam.task1.runner;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.service.HotelRequestBuffer;

public class ProducerTask implements Runnable {
    private HotelRequestBuffer hotelRequestBuffer;
    private HotelRequestRepository hotelRequestRepository;

    public ProducerTask(HotelRequestBuffer hotelRequestBuffer, HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestBuffer = hotelRequestBuffer;
        this.hotelRequestRepository = hotelRequestRepository;
    }

    @Override
    public void run() {
        while (!hotelRequestRepository.isRequestStackEmpty()) {
            hotelRequestBuffer.putRequest(hotelRequestRepository.getHotelRequest());
        }
    }
}
