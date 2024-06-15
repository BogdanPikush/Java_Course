package com.api.services;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class JwtService {
    private final String jwtSecret = "secret_key"; //secret key
    private final int jwtExpirationMs = 86400000; // 1 day

    public String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, jwtSecret)
                .setExpiration(new Date((new Date()).getTime() + jwtExpirationMs))
                .compact();
    }

    public String extractEmail(String token) {
        Claims claims = Jwts.parser()
                .setSigningKey(jwtSecret)
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    public String validateToken(String token) {
        try {
            System.out.println(Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token));
            Claims claims = Jwts.parser().setSigningKey(jwtSecret).parseClaimsJws(token).getBody();
            System.out.println(claims);
            return claims.getSubject(); // Возвращаем email пользователя
        } catch (Exception e) {
            System.err.println("Error validating token: " + e.getMessage()); // Добавлен отладочный вывод
            throw new IllegalArgumentException("Invalid token");
        }
    }

}
