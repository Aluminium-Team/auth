package com.aluminium.auth.controller;

import com.aluminium.auth.IO.signupIO.SignupInput;
import com.aluminium.auth.IO.signupIO.SignupOutput;
import com.aluminium.auth.service.SignupService;
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
    SignupService signupService;

    @PostMapping("/signup")
    public ResponseEntity<Object> signup(@RequestBody SignupInput request) {
        return signupService.signup(request.getUsername(), request.getEmail(), request.getPassword());
    }
}
