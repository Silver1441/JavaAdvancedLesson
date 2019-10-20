package com.kishkan.epam.task1.runner;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.service.HotelRequestBuffer;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequiredArgsConstructor
public class ProducerTask implements Runnable {
    @NonNull
    private HotelRequestBuffer hotelRequestBuffer;
    @NonNull
    private HotelRequestRepository hotelRequestRepository;

    @Override
    public void run() {
        log.info("->" + Thread.currentThread().getName() + " started it's work;");
        while (!hotelRequestRepository.isRequestStackEmpty()) {
            try {
                hotelRequestBuffer.putRequest(hotelRequestRepository.getHotelRequest());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        log.info("<-" + Thread.currentThread().getName() + " ended it's work;");
    }
}
