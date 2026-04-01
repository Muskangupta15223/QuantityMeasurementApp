package com.app.quantitymeasurement.config;

import java.security.Key;
import java.util.Date;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import jakarta.annotation.PostConstruct;

@Component
public class JwtUtil {
	
	@Value("${jwt.secretKey}")
	   private String SECRET;
	    private Key key;

	    @PostConstruct
	    public void init() {
	        this.key = Keys.hmacShaKeyFor(SECRET.getBytes());
	    }// 1 hour validity 
	 private final long EXPIRATION_TIME = 1000 * 60 * 60;
	 
	 // generate jwt token
	public String generateToken(String username) {
		return Jwts.builder().setSubject(username)
				.setIssuedAt(new Date())
				.setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
				.signWith(key, SignatureAlgorithm.HS256)
				.compact();
	}
	//extract username
	public String extractUsername(String token) {
		return extractClaims(token).getSubject();
	}
	
	//Validate token 
    public boolean validateToken(String token) {
        try {
            extractClaims(token);
            return true;
        } catch (JwtException | IllegalArgumentException e) {
            return false;
        }
    }
    
    // extract all claims
    private Claims extractClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }
}
