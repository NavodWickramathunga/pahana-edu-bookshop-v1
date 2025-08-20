package com.pahanaedu.billingsystem.service;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.repository.UserRepository;

@Service
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public UserService(PasswordEncoder passwordEncoder, UserRepository userRepository) {
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public boolean authenticateAdmin(String username, String password) {
        // Assuming you have a method to get the stored hashed password
        String storedHashedPassword = getStoredHashedPasswordForAdmin(username);
        if (storedHashedPassword != null) {
            return passwordEncoder.matches(password, storedHashedPassword);
        }
        return false; // Return false if user not found
    }

    private String getStoredHashedPasswordForAdmin(String username) {
        // Logic to retrieve the stored hashed password for the admin user
        User adminUser = userRepository.findByUsername(username);
        if (adminUser != null) {
            return adminUser.getPassword();
        }
        return null; // Return null if user not found
    }

    // Add these methods:
    public User registerUser(User user) {
        // Logic to register a new user
        // This should include encoding the password and saving the user to the database
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        // Save the user to the database
        return userRepository.save(user);
    }

    public boolean authenticate(String mobileNumber, String password) {
        // Logic to authenticate a user using mobile number and password
        String storedHashedPassword = getStoredHashedPasswordForUser(mobileNumber);
        if (storedHashedPassword != null) {
            return passwordEncoder.matches(password, storedHashedPassword);
        }
        return false; // Return false if user not found
    }

    private String getStoredHashedPasswordForUser(String mobileNumber) {
        // Logic to retrieve the stored hashed password for the user
        User user = userRepository.findByMobileNumber(mobileNumber);
        if (user != null) {
            return user.getPassword();
        }
        return null; // Return null if user not found
    }
}
