package com.deepak.quizapplication.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// Demo purpose
@RestController
public class RegistrationController {
    @GetMapping("/")
    public String greet(HttpServletRequest request) {
        return "Welcome to a new session: " + request.getSession().getId();
    }
}
