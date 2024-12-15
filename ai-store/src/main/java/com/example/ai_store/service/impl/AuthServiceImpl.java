package com.example.ai_store.service.impl;

import com.example.ai_store.config.JwtTokenProvider;
import com.example.ai_store.model.JwtResponse;
import com.example.ai_store.model.User;
import com.example.ai_store.model.UserRequest;
import com.example.ai_store.repository.UserRepository;
import com.example.ai_store.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthServiceImpl implements AuthService {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtTokenProvider jwtTokenProvider;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public JwtResponse login(UserRequest userRequest) {
        Optional<User> optionalUser = userRepository.findByUsername(userRequest.getUsername());

        User user = optionalUser.orElseThrow(() -> new RuntimeException("Username not found"));

        if (!passwordEncoder.matches(userRequest.getPassword(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenProvider.generateToken(user.getUsername());
        return new JwtResponse(token);
    }
}
