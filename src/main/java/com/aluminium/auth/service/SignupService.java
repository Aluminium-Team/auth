package com.aluminium.auth.service;

import com.aluminium.auth.IO.signupIO.SignupInput;
import com.aluminium.auth.IO.signupIO.SignupOutput;
import com.aluminium.auth.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class SignupService {

    @Autowired
    PasswordService passwordService;

    @Autowired
    UserService userService;

    public ResponseEntity<Object> signup(String username, String email, String password) {
        // Validate username
        if (username == null || username.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username is required.");
        }
        if (username.length() < 3 || username.length() > 20) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username must be between 3 and 20 characters.");
        }

        // Validate email
        if (email == null || email.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Email is required.");
        }
        if (!email.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Invalid email format.");
        }

        // Validate password
        if (password == null || password.isEmpty()) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password is required.");
        }
        if (password.length() < 2) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Password must be at least 6 characters long.");
        }

        String encrypted_password = passwordService.encryptPassword(password);

        User user;
        try {
             user = userService.createUser(username, email, encrypted_password);
        } catch (Exception e) {
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body("Username / email already used. "
                    + e.getMessage());
        }

        return new ResponseEntity<>(new SignupOutput(
                user.getUsername(),
                user.getEmail()
        ), HttpStatus.CREATED);

    }
}
