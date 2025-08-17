package com.pahanaedu.billingsystem.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.repository.UserRepository;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public boolean authenticateAdmin(String mobileNumber, String rawPassword) {
        Optional<User> userOpt = userRepository.findByMobileNumber(mobileNumber);
        return userOpt.isPresent() &&
                passwordEncoder.matches(rawPassword, userOpt.get().getPassword()) &&
                "ADMIN".equalsIgnoreCase(userOpt.get().getRole());
    }

    public User registerUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepository.save(user);
    }

    public boolean authenticate(String mobileNumber, String rawPassword) {
        Optional<User> userOpt = userRepository.findByMobileNumber(mobileNumber);
        return userOpt.isPresent() && passwordEncoder.matches(rawPassword, userOpt.get().getPassword());
    }
}
