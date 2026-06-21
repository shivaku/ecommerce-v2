package com.shiva.ecommerce_v2.security;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import io.jsonwebtoken.Claims;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {
      private static final String SECRET =
            "mysecretkeymysecretkeymysecretkeymysecretkey";

    public String generateToken(String username) {

        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis()
                        + 1000 * 60 * 60))
                .signWith(
                    getSigningKey()
                )
                .compact();
    }
    public String extractUsername(String token) {
        return extractClaims(token).getSubject();
    }
    public boolean isTokenExpired(String token) {
        return extractClaims(token).getExpiration().before(new Date());
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(
                        getSigningKey()
                )
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(
        String token,
        UserDetails userDetails
    ) {
        String username = extractUsername(token);
        return username.equals(userDetails.getUsername())
                && !isTokenExpired(token);
    }

    private SecretKey getSigningKey() {
        byte[] keyBytes = SECRET.getBytes(StandardCharsets.UTF_8);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
