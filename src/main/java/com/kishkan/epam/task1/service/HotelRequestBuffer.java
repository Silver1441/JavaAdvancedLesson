package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.dto.HotelRequest;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.logging.Logger;

public class HotelRequestBuffer {
    static Logger log = Logger.getLogger(HotelRequestBuffer.class.getName());
    private static final int BUFFER_CAPACITY = 5;
    private Deque<HotelRequest> requestsBuffer = new ArrayDeque<>();

    public synchronized void takeRequest() {
        while (requestsBuffer.isEmpty()) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestsBuffer.pollLast(); //Thread.currentThread().getName(),
        log.info(Thread.currentThread().getName() + "took request from the buffer");
        log.info("Currently " + requestsBuffer.size() + " requests in buffer;");
        notify();
    }

    public synchronized void putRequest(HotelRequest hotelRequest) {
        while (requestsBuffer.size() >= BUFFER_CAPACITY) {
            try {
                wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        requestsBuffer.addFirst(hotelRequest);
        log.info(Thread.currentThread().getName() + "put request in the buffer");
        log.info("Currently " + requestsBuffer.size() + " requests in buffer;");
    }
}
