package com.pahanaedu.billingsystem.repository;

import com.pahanaedu.billingsystem.model.Item;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ItemRepository extends MongoRepository<Item, Long> {
}