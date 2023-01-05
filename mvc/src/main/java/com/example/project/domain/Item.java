package com.example.project.domain;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class Item {

    private Long id;

    private String name;

    private int price;

    private int quantity;

    public Item(String name, int price, int quantity) {
        this.name = name;
        this.quantity = quantity;
        this.price = price;
    }
}
