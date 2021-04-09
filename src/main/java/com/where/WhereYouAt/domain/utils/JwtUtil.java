package com.where.WhereYouAt.domain.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;


public class JwtUtil {
    private Key key;

    public JwtUtil(String secret){
        this.key = Keys.hmacShaKeyFor(secret.getBytes());
    }

    public String createToken(Long userId, String nickname){
        String token = Jwts.builder()
                .setExpiration(new Date(300000))
                .claim("userId",userId)
                .claim("nickname",nickname)
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
        return token;
    }

    public Claims getClaims(String token){
        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build().parseClaimsJws(token)
                .getBody();

        return claims;
    }
}
