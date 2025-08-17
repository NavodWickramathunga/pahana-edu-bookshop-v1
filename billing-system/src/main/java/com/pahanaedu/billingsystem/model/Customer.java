package com.pahanaedu.billingsystem.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "customers")
public class Customer {
    @Id
    private String accountNumber; // Unique
    private String name;
    private String address;
    private String telephone;
    private int unitsConsumed;
}
