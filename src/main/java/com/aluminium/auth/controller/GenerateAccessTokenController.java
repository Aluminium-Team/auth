package com.aluminium.auth.controller;

import com.aluminium.auth.DTO.TokensDto;
import com.aluminium.auth.service.AccessService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class GenerateAccessTokenController {

    @Autowired
    AccessService accessService;

    @PostMapping("/api/v1/generateAccessToken")
    public ResponseEntity<String> generateAccessToken(@RequestBody TokensDto request) {

        String refreshToken = request.getRefreshToken();

        try {
            String accessToken = accessService.validateRefreshToken(refreshToken);
            return new ResponseEntity<>(accessToken, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(null, HttpStatus.UNAUTHORIZED);
        }
    }
}
