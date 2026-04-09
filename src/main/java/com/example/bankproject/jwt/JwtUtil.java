package com.example.bankproject.jwt;

import java.nio.charset.StandardCharsets;
import java.util.Date;

import javax.crypto.SecretKey;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.example.bankproject.dto.Role;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

@Component
public class JwtUtil {
	private final SecretKey sk;
	private final long expiration;
	
	public JwtUtil(@Value("${jwt.secret}") String secret, @Value("${jwt.expiration}") long expiration) {
		this.sk = Keys.hmacShaKeyFor(secret.getBytes(StandardCharsets.UTF_8));
		this.expiration = expiration;
	}
	
	public String generateToken(String username, Role role, String name) {
		return Jwts.builder().subject(username).claim("role", role).claim("name", name).issuedAt(new Date()).expiration(new Date(System.currentTimeMillis() + expiration)).signWith(sk).compact();
	}
	
	public Claims parseToken(String token) {
		return Jwts.parser().verifyWith(sk).build().parseSignedClaims(token).getPayload();
	}
	
	public boolean isValid(String token) {
		try {
			parseToken(token);
			return true;
		} catch(Exception e) {
			return false;
		}
	}
	
	public String getId(String token) {
		return parseToken(token).getSubject();
	}
	
	public String getRole(String token) {
		return parseToken(token).get("role", String.class);
	}
	
	public String getUsername(String token) {
		return parseToken(token).get("name", String.class);
	}
}
