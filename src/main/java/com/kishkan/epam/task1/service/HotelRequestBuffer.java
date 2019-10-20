package com.kishkan.epam.task1.service;

import com.kishkan.epam.task1.dto.HotelRequest;
import com.kishkan.epam.task1.repository.HotelRequestRepository;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayDeque;
import java.util.Deque;

@Slf4j
public class HotelRequestBuffer {
    private int bufferCapacity = 5;
    private HotelRequestRepository hotelRequestRepository;
    private Deque<HotelRequest> requestsBuffer = new ArrayDeque<>();

    public HotelRequestBuffer(HotelRequestRepository hotelRequestRepository) {
        this.hotelRequestRepository = hotelRequestRepository;
        log.info("->Buffer was created;");
    }

    public HotelRequestBuffer(HotelRequestRepository hotelRequestRepository, int bufferCapacity) {
        this.hotelRequestRepository = hotelRequestRepository;
        this.bufferCapacity = bufferCapacity;
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
        while (requestsBuffer.size() >= bufferCapacity) {
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

    public int getBufferCapacity() {
        return bufferCapacity;
    }

    public void setBufferCapacity(int bufferCapacity) {
        this.bufferCapacity = bufferCapacity;
    }
}
