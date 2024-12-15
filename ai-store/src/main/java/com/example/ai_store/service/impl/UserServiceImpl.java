package com.example.ai_store.service.impl;

import com.example.ai_store.model.User;
import com.example.ai_store.model.UserRequest;
import com.example.ai_store.repository.UserRepository;
import com.example.ai_store.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public User createUser (UserRequest userRequest) {
        if(userRepository.findByUsername(userRequest.getUsername()) != null) {
            throw new RuntimeException("Username already exists");
        }

        String encodePassword = passwordEncoder.encode(userRequest.getPassword());

        User newUser = new User();
        newUser.setUsername(userRequest.getUsername());
        newUser.setPassword(encodePassword);

        return userRepository.save(newUser);
    }
}
