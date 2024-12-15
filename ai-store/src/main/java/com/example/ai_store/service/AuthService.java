package com.example.ai_store.service;

import com.example.ai_store.model.JwtResponse;
import com.example.ai_store.model.UserRequest;
import org.springframework.stereotype.Service;

public interface AuthService {

   JwtResponse login(UserRequest userRequest);
}
