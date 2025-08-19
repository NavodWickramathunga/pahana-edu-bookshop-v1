// src/main/java/com/pahanaedu/billingsystem/repository/ItemRepository.java
package com.pahanaedu.billingsystem.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pahanaedu.billingsystem.model.Item;

public interface ItemRepository extends MongoRepository<Item, Long> { // Change String to Long if Item ID is Long
}
