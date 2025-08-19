// src/main/java/com/pahanaedu/billingsystem/controller/CustomerAuthController.java
package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.Customer;
import com.pahanaedu.billingsystem.repository.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/customers")
public class CustomerAuthController {

    @Autowired
    private CustomerRepository customerRepository;

    // Register new customer
    @PostMapping("/register")
    public String register(@RequestBody Customer customer) {
        if (customerRepository.findByUsername(customer.getUsername()).isPresent()) {
            return "Username already exists!";
        }
        customerRepository.save(customer);
        return "Customer registered successfully!";
    }

    // Customer login
    @PostMapping("/login")
    public String login(@RequestBody Customer customer) {
        Optional<Customer> existing = customerRepository.findByUsername(customer.getUsername());
        if (existing.isPresent() && existing.get().getPassword().equals(customer.getPassword())) {
            return "Customer login successful!";
        }
        return "Invalid username or password!";
    }
}
