package com.aluminium.auth.controller;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SampleController {

    @GetMapping("/api/v1")
    public ResponseEntity<String> greeting(HttpServletRequest request) {
        return ResponseEntity.ok("Hello There, Your Session ID = "
                + request.getSession().getId());
    }

}
