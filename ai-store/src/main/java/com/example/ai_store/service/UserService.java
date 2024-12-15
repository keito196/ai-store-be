package com.example.ai_store.service;
import com.example.ai_store.model.User;
import com.example.ai_store.model.UserRequest;
import org.springframework.stereotype.Service;

import java.util.Optional;

public interface UserService {
    Optional<User> findByUsername(String username);

    User createUser(UserRequest userRequest);
}
