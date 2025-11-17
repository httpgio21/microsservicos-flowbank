package com.ms.conta.security;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    private static final String SECRET = "minhaChaveSecretaSuperSegura123"; // mesma do UsuarioService

    // Cria a chave de forma segura
    private static final Key KEY = Keys.hmacShaKeyFor(SECRET.getBytes());

    // Gera token JWT com validade de 1 hora
    public static String generateToken(String email) {
        return Jwts.builder()
                .setSubject(email)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 60 * 60 * 1000)) // 1h
                .signWith(KEY, SignatureAlgorithm.HS256)
                .compact();
    }

    // Valida token JWT
    public static boolean validateToken(String token) {
        try {
            Jwts.parserBuilder()
                .setSigningKey(KEY)
                .build()
                .parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
