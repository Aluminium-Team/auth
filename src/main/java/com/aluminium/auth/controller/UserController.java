package com.aluminium.auth.controller;

import com.aluminium.auth.DTO.TokensDto;
import com.aluminium.auth.DTO.UserDto;
import com.aluminium.auth.service.UserService;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;
import java.util.Objects;

@RestController
@RequestMapping(path = "/api/v1/users")
public class UserController {

    @Autowired
    UserService userService;

    @GetMapping("/info")
    public ResponseEntity<Map<String, Object>> getUserInfo(@RequestBody TokensDto request) {
        String accessToken = request.getAccessToken();

        return userService.getUserInfo(accessToken);
    }

}
