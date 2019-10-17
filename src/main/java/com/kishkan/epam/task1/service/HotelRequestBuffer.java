package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.dto.HotelRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

public class HotelRequestBuffer {
    private static Logger log = Logger.getLogger(HotelRequestBuffer.class.getName());
    private static final int BUFFER_CAPACITY = 5;
    private Deque<HotelRequest> requestsBuffer = new ArrayDeque<>();

    public HotelRequestBuffer() {
        log.info("buffer was created;");
    }

    public synchronized boolean takeRequest() {
        while (requestsBuffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestsBuffer.pollLast();
        log.info(Thread.currentThread().getName() + " took request from the buffer, currently "
                + requestsBuffer.size() + " requests in buffer;");
        notifyAll();
        return true;
    }

    public synchronized boolean putRequest(HotelRequest hotelRequest) {
        while (requestsBuffer.size() >= BUFFER_CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestsBuffer.addFirst(hotelRequest);
        log.info(Thread.currentThread().getName() + " put request in the buffer, currently "
                + requestsBuffer.size() + " requests in buffer;");
        notifyAll();
        return true;
    }

    public synchronized boolean isBufferEmpty() {
        return requestsBuffer.isEmpty();
    }
}
