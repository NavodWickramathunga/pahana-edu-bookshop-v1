package com.pahanaedu.billingsystem.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

@Entity
@Table(name = "items")
public class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;
    private Integer stock; // Renamed from quantity to stock

    public Item() {}

    public Item(String name, Double price, Integer stock) { // Updated constructor parameter
        this.name = name;
        this.price = price;
        this.stock = stock; // Updated assignment
    }

    // Getters & Setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public Double getPrice() { return price; }
    public void setPrice(Double price) { this.price = price; }

    public Integer getStock() { return stock; } // Updated getter
    public void setStock(Integer stock) { this.stock = stock; } // Updated setter
}
