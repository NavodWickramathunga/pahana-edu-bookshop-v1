package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    // ------------------- REGISTER -------------------
    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    // ------------------- LOGIN -------------------
    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody User loginRequest) {
        boolean success = userService.authenticate(
                loginRequest.getUsername(),
                loginRequest.getPassword()
        );

        if (success) {
            return ResponseEntity.ok("Login Successful");
        } else {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body("Invalid Credentials");
        }
    }
}
