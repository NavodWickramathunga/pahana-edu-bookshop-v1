package com.pahanaedu.billingsystem.dto;

import com.pahanaedu.billingsystem.model.Customer;

public class AddCustomerRequest {
    private Customer customer;

    // Default constructor
    public AddCustomerRequest() {}

    // Constructor with customer
    public AddCustomerRequest(Customer customer) {
        this.customer = customer;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }
}