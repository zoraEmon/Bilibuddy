package com.example.biliBuddy.common.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtService {

    private SecretKey secretKey;
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 2; // 2 hours before it expires.
    private final long REFRESH_EXPIRATION_TIME = 1000 * 60 * 60 * 24 * 7; //7 days before it expires, keeps the user logged in.

    @PostConstruct
    public void init() {
        String secret = "Bilibuddy-will-be-done-before-this-year-ends-so-it-means-I-still-have-6-months";
        this.secretKey = Keys.hmacShaKeyFor(secret.getBytes());
    }

    // Generate JWT with subject and claims.
    public String generateToken(String subject, Map<String, Object> extraClaims) {
        return Jwts.builder()
                .claims(extraClaims)
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    //Extract username/email from token (subject).
    public String extractEmail(String token) {
        return extractClaims(token, Claims::getSubject);
    }

    //Token CHECK!!
    public boolean isTokenValid(String token, String expectedEmail) {
        final String username = extractEmail(token);
        return username.equals(expectedEmail)  && isTokenExpired(token);
    }

    //Check if token EXPIRED.
    public boolean isTokenExpired(String token) {
        return extractClaims(token, Claims::getExpiration).before(new Date());
    }

    private <T> T extractClaims(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(secretKey)
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    //For creating refresh token.
    public String generateRefreshToken(String subject) {
        return Jwts.builder()
                .subject(subject)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + REFRESH_EXPIRATION_TIME))
                .signWith(secretKey, Jwts.SIG.HS256)
                .compact();
    }

    public long getExpirationTime() {
        return System.currentTimeMillis() + EXPIRATION_TIME;
    }
}
