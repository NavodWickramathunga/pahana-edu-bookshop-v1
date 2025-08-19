package com.pahanaedu.billingsystem.service;

import com.pahanaedu.billingsystem.model.User;
import org.springframework.security.crypto.password.PasswordEncoder; // Import PasswordEncoder
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder; // Inject PasswordEncoder

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

    // Add these methods:
    public User registerUser(User user) {
        // Logic to register a new user
        // This should include encoding the password and saving the user to the database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // Save the user to the database
        return user; // Return the saved user
    }

    public boolean authenticate(String mobileNumber, String password) {
        // Logic to authenticate a user using mobile number and password
        String storedHashedPassword = getStoredHashedPasswordForUser(mobileNumber); // Implement this method
        return passwordEncoder.matches(password, storedHashedPassword);
    }

    private String getStoredHashedPasswordForUser(String mobileNumber) {
        // Logic to retrieve the stored hashed password for the user
        // This could be from a database or a properties file
        return "$2a$10$YOUR_BCRYPT_HASH_HERE"; // Replace with actual retrieval logic
    }
}
