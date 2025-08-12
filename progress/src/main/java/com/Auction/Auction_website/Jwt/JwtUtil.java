package com.Auction.Auction_website.Jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
@Component
public class JwtUtil {
    private static final String SECRET_KEY="MyJwtSecretKeyForAuctionWebsite12345";
    // Token valid for 24 hours
    private static final long EXPIRATION_TIME = 1000 * 60 * 60 * 24;
    private Key getSigningKey() {
        return Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
    }
    public String generateToken(String email,String role){
        return Jwts.builder()
                .setSubject(email)
                .claim("role", role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(getSigningKey(), SignatureAlgorithm.HS256)
                .compact();
    }
    // 2️⃣ Extract Email from Token
    public String extractEmail(String token) {
        return parseToken(token).getBody().getSubject();
    }

    // 3️⃣ Extract Role
    public String extractRole(String token) {
        return parseToken(token).getBody().get("role", String.class);
    }

    // 4️⃣ Validate token expiration
    public boolean isTokenExpired(String token) {
        return parseToken(token).getBody().getExpiration().before(new Date());
    }

    // 5️⃣ Final check
    public boolean validateToken(String token, String email) {
        return email.equals(extractEmail(token)) && !isTokenExpired(token);
    }

    // ✳️ Internal - parse token
    private Jws<Claims> parseToken(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(getSigningKey())
                .build()
                .parseClaimsJws(token);
    }
}
