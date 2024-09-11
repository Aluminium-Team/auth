package com.aluminium.auth.service;

import com.aluminium.auth.model.Token;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AccessService {

    @Autowired
    private TokenService tokenService;

    @Autowired
    JwtUtils jwtUtils;

    public String validateAccessToken(String token) {
        if (!jwtUtils.isExpired(token)) throw new IllegalArgumentException("Access token expired");

        return token;
    }

    public String validateRefreshToken(String token) {
        if (!jwtUtils.isExpired(token)) throw new IllegalArgumentException("Refresh token expired");

        final UUID requestJti = jwtUtils.extractJti(token);
        final Token tokenFromDB = tokenService.getUserByJti(requestJti);

        final UUID databaseJti = tokenFromDB.getJti();

        if (!databaseJti.equals(requestJti) || !tokenFromDB.getIsLoggedIn()) {
            throw new IllegalArgumentException("Refresh token holds an incorrect JTI");
        }

        return jwtUtils.generateToken(tokenFromDB.getUser());
    }

}
