package com.pahanaedu.billingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pahanaedu.billingsystem.model.Cart;

public interface CartRepository extends MongoRepository<Cart, Long> {
}