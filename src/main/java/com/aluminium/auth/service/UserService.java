package com.aluminium.auth.service;

import com.aluminium.auth.DTO.UserDto;
import com.aluminium.auth.model.User;
import com.aluminium.auth.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AccessService accessService;

    @Autowired
    private JwtUtils jwtUtils;

    public User getUserById(UUID id) {
        Optional<User> userOptional = userRepository.findById(id);
        return userOptional.orElseGet(User::new);
    }

    public ResponseEntity<Map<String, Object>> getUserInfo(String accessToken) {
        try {
            accessService.validateAccessToken(accessToken);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        User user;
        try {
            UUID userId = jwtUtils.extractUserId(accessToken);
            user = userRepository.findById(userId).orElseThrow(Exception::new);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }

        return new ResponseEntity<>(
                Map.of("username", user.getUsername(),
                        "email", user.getEmail(),
                        "created_at", user.getCreatedAt())
                , HttpStatus.OK);
    }
}
