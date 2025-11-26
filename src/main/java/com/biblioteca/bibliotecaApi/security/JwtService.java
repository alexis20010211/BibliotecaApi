package com.biblioteca.bibliotecaApi.security;

import java.security.Key;
import java.util.Date;

import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

@Service
public class JwtService {

    private final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    private final long EXPIRATION_TIME = 1000 * 60 * 60 * 24; // 24 horas

    // Genera un token a partir del username
    public String generateToken(String username) {
        return Jwts.builder()
                .setSubject(username)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(key)
                .compact();
    }

    // Extrae el username del token
    public String extraerUsername(String token) {
        return getClaims(token).getSubject();
    }

    // Valida que el token sea válido y coincida con el usuario
    public boolean validarToken(String token, CustomUserDetails userDetails) {
        final String username = extraerUsername(token);
        return (username.equals(userDetails.getUsername()) && !estaExpirado(token));
    }

    // Verifica si el token ha expirado
    private boolean estaExpirado(String token) {
        return getClaims(token).getExpiration().before(new Date());
    }

    // Obtiene los claims del token
    private Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
