package com.aluminium.auth.service;

import com.aluminium.auth.model.Token;
import com.aluminium.auth.model.User;
import com.aluminium.auth.repository.TokenRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;
import java.util.UUID;

@Service
public class TokenService {

    @Autowired
    private TokenRepository tokenRepository;

    public Token getUserByJti(UUID jti) {
        Optional<Token> tokenOptional = tokenRepository.findById(jti);
        return tokenOptional.orElseGet(Token::new);
    }

    public Token createRefreshToken(User user) {
        Token token = new Token(user);
        return tokenRepository.save(token);
    }

}
