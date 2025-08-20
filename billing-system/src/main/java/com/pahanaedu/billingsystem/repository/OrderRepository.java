package com.pahanaedu.billingsystem.repository;

import com.pahanaedu.billingsystem.model.Order;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface OrderRepository extends MongoRepository<Order, Long> {
}