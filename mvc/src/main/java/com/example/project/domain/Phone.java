package com.example.project.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Phone {

    private Long id;

    private String name;

    private String company;

    private int price;

    public Phone(String name, String company, int price) {
        this.name = name;
        this.company = company;
        this.price = price;
    }
}
