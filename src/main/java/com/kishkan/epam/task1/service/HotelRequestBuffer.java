package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.dto.HotelRequest;
import com.kishkan.epam.task1.repository.HotelRequestRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayDeque;
import java.util.Deque;

public class HotelRequestBuffer {
    private static Logger log = LoggerFactory.getLogger(HotelRequestBuffer.class);
    private static final int BUFFER_CAPACITY = 5;
    private HotelRequestRepository hotelRequestRepository;
    private Deque<HotelRequest> requestsBuffer = new ArrayDeque<>();

    public HotelRequestBuffer(HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestRepository = hotelRequestRepository;
        log.info("->Buffer was created;");
    }

    public synchronized void takeRequest() throws InterruptedException {
        while (requestsBuffer.isEmpty() && !hotelRequestRepository.isRequestStackEmpty()) {
            wait();
        }
        if(isBufferNotEmpty()) {
            requestsBuffer.pollLast();
            log.info(Thread.currentThread().getName() + " took request from the buffer, currently "
                    + requestsBuffer.size() + " requests in buffer;");
            notifyAll();
        }
    }

    public synchronized void putRequest(HotelRequest hotelRequest) throws InterruptedException {
        while (requestsBuffer.size() >= BUFFER_CAPACITY) {
            wait();
        }
        requestsBuffer.addFirst(hotelRequest);
        log.info(Thread.currentThread().getName() + " put request in the buffer, currently "
                + requestsBuffer.size() + " requests in buffer;");
        notifyAll();
    }

    public synchronized boolean isBufferNotEmpty() {
        return !requestsBuffer.isEmpty();
    }
}
