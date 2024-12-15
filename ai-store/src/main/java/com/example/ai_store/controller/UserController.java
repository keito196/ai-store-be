package com.example.ai_store.controller;

import com.example.ai_store.model.User;
import com.example.ai_store.model.UserRequest;
import com.example.ai_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "http://localhost:3000")
public class UserController {
    @Autowired
    private UserService userService;

    @PostMapping("/users/create")
    public ResponseEntity<User> createUser(@RequestBody UserRequest userRequest) {
        try {
            User newUser = userService.createUser(userRequest);
            return new ResponseEntity<>(newUser, HttpStatus.CREATED);
        } catch (RuntimeException e) {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }
}
