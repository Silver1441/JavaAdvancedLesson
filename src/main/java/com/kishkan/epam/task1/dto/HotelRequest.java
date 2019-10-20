package com.kishkan.epam.task1.dto;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
public class HotelRequest {
    private long ad;
    private LocalDate date;
    private String hotel;

    public HotelRequest(long ad, String hotel) {
        this.ad = ad;
        this.hotel = hotel;
        this.date = LocalDate.now();
    }
}
