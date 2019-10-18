package com.kishkan.epam.task1.runner;

import com.kishkan.epam.task1.repository.HotelRequestRepository;
import com.kishkan.epam.task1.service.HotelRequestBuffer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ConsumerTask implements Runnable {
    private static Logger log = LoggerFactory.getLogger(ConsumerTask.class);
    private HotelRequestBuffer hotelRequestBuffer;
    private HotelRequestRepository hotelRequestRepository;

    public ConsumerTask(HotelRequestBuffer hotelRequestBuffer, HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestBuffer = hotelRequestBuffer;
        this.hotelRequestRepository = hotelRequestRepository;
    }

    @Override
    public void run() {
        log.info("->" + Thread.currentThread().getName() + " started it's work;");
        while(hotelRequestBuffer.isBufferNotEmpty() || !hotelRequestRepository.isRequestStackEmpty()) {
            try {
                hotelRequestBuffer.takeRequest();
                Thread.sleep(500);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }
        log.info("<-" + Thread.currentThread().getName() + " ended it's work;");
    }
}
