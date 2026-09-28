package com.college.roombook.controller;

import com.college.roombook.service.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class LoginController {

    private final UserService userService;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Map<String, String> request) {

        String username = request.get("username");
        String password = request.get("password");

        if (userService.login(username, password)) {
            return Map.of(
                    "success", true,
                    "message", "Login successful"
            );
        }

        return Map.of(
                "success", false,
                "message", "Invalid username or password"
        );
    }
}