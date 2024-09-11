package com.aluminium.auth.controller;

import com.aluminium.auth.IO.LoginIO.LoginInput;
import com.aluminium.auth.IO.signupIO.SignupInput;
import com.aluminium.auth.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1")
public class AuthController {

    @Autowired
    AuthService authService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupInput request) {
        return authService.signup(request.getUsername(), request.getEmail(), request.getPassword());
    }

    @PostMapping("/login")
    public ResponseEntity<Object> login(@RequestBody LoginInput request) {
        return authService.login(request.getEmail(), request.getPassword());
    }
}
