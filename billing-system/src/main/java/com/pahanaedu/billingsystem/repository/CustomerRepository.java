package com.pahanaedu.billingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pahanaedu.billingsystem.model.Customer;

public interface CustomerRepository extends MongoRepository<Customer, String> { }
