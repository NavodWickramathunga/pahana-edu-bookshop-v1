package com.pahanaedu.billingsystem.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.pahanaedu.billingsystem.model.User;

public interface UserRepository extends MongoRepository<User, String> {
    Optional<User> findByMobileNumber(String mobileNumber);
}
