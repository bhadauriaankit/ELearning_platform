package com.ankit.elearning.controller;

import com.ankit.elearning.entity.User;
import com.ankit.elearning.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/register")
    public User registerUser(@RequestBody User user) {
        return userService.registerUser(user);
    }
    @PostMapping("/login")
    public String loginUser(@RequestParam String email,
                            @RequestParam String password) {
        return userService.loginUser(email, password);
    }
    @GetMapping("/test")
    public String test() {
        return "Protected API working";
    }
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOnly() {
        return "Admin access granted";
    }
}