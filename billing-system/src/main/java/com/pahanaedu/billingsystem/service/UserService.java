package com.pahanaedu.billingsystem.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public boolean authenticateAdmin(String username, String password) {
        // Assuming you have a method to get the stored hashed password
        String storedHashedPassword = getStoredHashedPasswordForAdmin(username); // Implement this method
        return passwordEncoder.matches(password, storedHashedPassword);
    }

    private String getStoredHashedPasswordForAdmin(String username) {
        // Logic to retrieve the stored hashed password for the admin user
        // This could be from a database or a properties file
        return "$2a$10$YOUR_BCRYPT_HASH_HERE"; // Replace with actual retrieval logic
    }
}
