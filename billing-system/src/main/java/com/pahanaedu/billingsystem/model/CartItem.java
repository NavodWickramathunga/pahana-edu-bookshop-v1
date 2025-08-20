package com.pahanaedu.billingsystem.model;

import lombok.Data;

@Data
public class CartItem {
    private Long itemId;
    private String name;
    private Double price;
    private Integer quantity;

    public CartItem() {}

    public CartItem(Long itemId, String name, Double price, Integer quantity) {
        this.itemId = itemId;
        this.name = name;
        this.price = price;
        this.quantity = quantity;
    }
}


