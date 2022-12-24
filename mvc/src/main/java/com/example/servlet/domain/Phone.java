package com.example.servlet.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class Phone {

    private long id;

    private String name;

    private int price;

    public Phone(String name, int price) {
        this.name = name;
        this.price = price;
    }
}