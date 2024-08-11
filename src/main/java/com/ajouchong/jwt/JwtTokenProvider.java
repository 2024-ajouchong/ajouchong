package com.ajouchong.jwt;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class JwtTokenProvider {

    @Value("${jwt.secret}")
    private String jwt_secret;

    @Value("${jwt.expiration_time}")
    private int expiration_time;

    // 토큰 생성
    public String generateToken(String username) {
        Date now = new Date();
        Date expiryDate = new Date(now.getTime() + expiration_time);

        Map<String, Object> claims = new HashMap<>();
        claims.put("sub", username);

        Key key = new SecretKeySpec(jwt_secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        return Jwts.builder()
                .setClaims(claims)
                .setIssuedAt(now)
                .setExpiration(expiryDate)
                .signWith(key, SignatureAlgorithm.HS512)
                .compact();
    }

    // 토큰에서 사용자 이름 추출
    public String getUsernameFromJWT(String token) {
        Key key = new SecretKeySpec(jwt_secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();

        return claims.getSubject();
    }

    // 토큰 유효성 검사
    public boolean validateToken(String authToken) {
        try {
            Key key = new SecretKeySpec(jwt_secret.getBytes(), SignatureAlgorithm.HS512.getJcaName());
            Jwts.parserBuilder().setSigningKey(key).build().parseClaimsJws(authToken);

            return true;
        } catch (JwtException | IllegalArgumentException e) {
            System.out.println("유효하지 않은 JWT 토큰: " + e.getMessage());
        }
        return false;
    }
}
