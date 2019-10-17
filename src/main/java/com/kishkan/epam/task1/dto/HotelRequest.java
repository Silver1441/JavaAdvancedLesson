package com.kishkan.epam.task1.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Setter
@Getter
@AllArgsConstructor
public class HotelRequest {
    private long ad;
    private LocalDate date;
    private String hotel;
}
