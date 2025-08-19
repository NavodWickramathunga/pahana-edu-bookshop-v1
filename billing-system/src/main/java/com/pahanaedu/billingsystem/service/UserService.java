package com.pahanaedu.billingsystem.service;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;
    
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public UserService(PasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

    public User registerUser(User user) {
        // Hash the password before saving
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticate(String username, String password) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            return passwordEncoder.matches(password, user.getPassword());
        }
        return false;
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
