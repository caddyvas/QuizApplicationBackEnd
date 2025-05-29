package com.deepak.quizapplication.controller;

import com.deepak.quizapplication.exception.UserValidationException;
import com.deepak.quizapplication.model.Users;
import com.deepak.quizapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UsersController {

    @Autowired
    UserService userService;

    @PostMapping(value = "signup", consumes = "application/json")
    public ResponseEntity<String> signUp(@RequestBody Users user) {
        return userService.addUser(user);
    }

    /* Using @PathVariable
    @GetMapping("login/{username}")
    public ResponseEntity<Object> login(@PathVariable String username) {
        return userService.login(username);
    } */

    //using @RequestParam - http://xxxxxxxxx/users/login?username=xxxx&password=xxxxx
    @GetMapping("login")
    public ResponseEntity<Object> login(@RequestParam String username, @RequestParam String password) throws UserValidationException {
        return userService.login(username);
    }
}
