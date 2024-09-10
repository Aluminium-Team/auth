package com.aluminium.auth.service;

import com.aluminium.auth.model.User;
import com.aluminium.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseGet(User::new);
    }
}
