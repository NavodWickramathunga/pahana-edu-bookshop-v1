package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public ResponseEntity<?> register(@RequestBody User user) {
        return ResponseEntity.ok(userService.registerUser(user));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestParam String mobile,
                                   @RequestParam String password) {
        boolean success = userService.authenticate(mobile, password);
        if (success) return ResponseEntity.ok("Login Successful");
        else return ResponseEntity.status(401).body("Invalid Credentials");
    }
}
