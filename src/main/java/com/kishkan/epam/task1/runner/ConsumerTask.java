package com.kishkan.epam.task1.runner;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.service.HotelRequestBuffer;

public class ConsumerTask implements Runnable {
    private HotelRequestBuffer hotelRequestBuffer;
    private HotelRequestRepository hotelRequestRepository;

    public ConsumerTask(HotelRequestBuffer hotelRequestBuffer, HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestBuffer = hotelRequestBuffer;
        this.hotelRequestRepository = hotelRequestRepository;
    }

    @Override
    public void run() {
        while(!hotelRequestBuffer.isBufferEmpty() || !hotelRequestRepository.isRequestStackEmpty()) {
            hotelRequestBuffer.takeRequest();
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
