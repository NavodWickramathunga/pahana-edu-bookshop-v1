package com.pahanaedu.billingsystem.controller;

import com.pahanaedu.billingsystem.model.User;
import com.pahanaedu.billingsystem.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
@CrossOrigin
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User register(@RequestBody User user) {
        return userService.registerUser(user);
    }

    @PostMapping("/login")
    public String login(@RequestBody User user) {
        return userService.authenticate(user.getMobileNumber(), user.getPassword())
                ? "Login Successful"
                : "Invalid Credentials";
    }
}
