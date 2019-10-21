package com.kishkan.epam.task2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.io.Serializable;

@Setter
@Getter
@ToString
@AllArgsConstructor
public class Account implements Serializable {
    private final long SERIAL_VERSION_ID = 0;

    private String name;
    private String surname;
    private long id;
    private long balance;

}
