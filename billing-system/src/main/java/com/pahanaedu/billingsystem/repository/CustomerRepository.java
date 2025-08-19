// src/main/java/com/pahanaedu/billingsystem/repository/CustomerRepository.java
package com.pahanaedu.billingsystem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pahanaedu.billingsystem.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, Long> { // Change String to Long
    Optional<Customer> findByUsername(String username);
}
