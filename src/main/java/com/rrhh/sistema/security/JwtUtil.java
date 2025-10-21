package com.rrhh.sistema.security;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	
	// 🔑 Clave secreta (min 64 caracteres para HS512)
    private final String SECRET_STRING = "JWT_Secret_Key_For_Testing_Purposes_Only_Do_Not_Use_In_Production_2024!!";
    private final SecretKey SECRET_KEY = Keys.hmacShaKeyFor(SECRET_STRING.getBytes());
    // ⏳ 10 días en milisegundos
    private final long EXPIRATION_TIME = 864_000_000;

    // ✅ Genera el token
    public String generateToken(String email, String role) {
        Map<String, Object> claims = new HashMap<>();
        claims.put("role", role);

        return Jwts.builder()
        		.claims(claims)
                .subject(email)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SECRET_KEY)
                .compact();    
    }

    // ✅ Valida el token
    public Boolean validateToken(String token, String email) {
        final String username = extractUsername(token);
        return (username.equals(email) && !isTokenExpired(token));
    }

    // ✅ Extrae el email
    public String extractUsername(String token) {
        return extractAllClaims(token).getSubject();
    }

    // ✅ Extrae el rol
    public String extractRole(String token) {
        return (String) extractAllClaims(token).get("role");
    }

    // ✅ Obtiene todos los claims
    private Claims extractAllClaims(String token) {
    	JwtParser jwtParser = Jwts.parser()
                .verifyWith(SECRET_KEY)
                .build();

    	return jwtParser.parseSignedClaims(token).getPayload();
    }

    // ✅ Verifica si expiró
    private Boolean isTokenExpired(String token) {
        return extractAllClaims(token).getExpiration().before(new Date());
    }

}
