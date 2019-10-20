package com.kishkan.epam.task2.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
public class Account {
    private String name;
    private String surname;
    private long id;
    private long balance;
}
