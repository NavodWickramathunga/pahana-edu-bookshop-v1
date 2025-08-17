package com.pahanaedu.billingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.pahanaedu.billingsystem.model.Customer;
import com.pahanaedu.billingsystem.repository.CustomerRepository;

@Service
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    public Customer addCustomer(Customer customer) {
        // Save the customer to the database
        return customerRepository.save(customer);
    }
}
