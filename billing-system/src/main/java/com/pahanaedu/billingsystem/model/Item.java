package com.pahanaedu.billingsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

public class Item {

    // model/Item.java
@Data
@Document(collection = "items")
public class Item {
    @Id
    private String id;
    private String name;
    private double price;
    private int stock;
}

    
}
