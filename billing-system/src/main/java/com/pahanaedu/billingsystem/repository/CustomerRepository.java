package com.pahanaedu.billingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.pahanaedu.billingsystem.model.Customer;

@Repository
public interface CustomerRepository extends MongoRepository<Customer, String> {
    // Additional query methods can be defined here if needed
}
