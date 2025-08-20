package com.pahanaedu.billingsystem.repository;

import com.pahanaedu.billingsystem.model.Bill;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface BillRepository extends MongoRepository<Bill, Long> {
}
