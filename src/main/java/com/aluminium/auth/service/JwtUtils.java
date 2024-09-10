package com.aluminium.auth.service;

import com.aluminium.auth.model.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.NonNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.UUID;

@Component
public class JwtUtils {

    @Autowired
    private ParserService parserService;

    private final static SecretKey key = Jwts.SIG.HS256.key().build();
    private final static long ACCESS_TOKEN_TIME = 15 * 60 * 1000;
    private final static long REFRESH_TOKEN_TIME = 6L * 30 * 24 * 60 * 60 * 1000;

    public boolean isValid(String token) {
        Claims claims = extractClaims(token);
        Date expiration = claims.getExpiration();

        return expiration != null && expiration.after(new Date());
    }

    /*
     * Only Works for Refresh Token.
     */
    public UUID extractJti(String token) {
        Claims claims = extractClaims(token);

        return parserService.stringToUUID(claims.getId());
    }

    public UUID extractUserId(String token) {
        Claims claims = extractClaims(token);

        return parserService.stringToUUID(claims.getSubject());
    }

    /*
     * Access Token Generation.
     */
    public String generateToken(@NonNull User user) {
        return Jwts.builder()
                .subject(user.getId().toString()) // User ID as the subject
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_TIME))
                .signWith(key)
                .compact();
    }

    /*
     * Refresh Token Generation.
     */
    public String generateToken(@NonNull User user, @NonNull UUID jti) {
        return Jwts.builder()
                .subject(user.getId().toString()) // User ID as the subject
                .id(jti.toString())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_TOKEN_TIME))
                .signWith(key)
                .compact();
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(key)
                .build()
                .parseSignedClaims(token.replace("Bearer ", ""))
                .getPayload();
    }
}
