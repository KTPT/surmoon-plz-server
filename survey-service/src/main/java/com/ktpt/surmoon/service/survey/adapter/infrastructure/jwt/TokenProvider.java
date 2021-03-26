package com.ktpt.surmoon.service.survey.adapter.infrastructure.jwt;

import io.jsonwebtoken.*;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class TokenProvider {
    private static final String SECRET_KEY = "temp_secret_key";
    private static final long VALIDATION_INTERVAL = 3600000;

    public String createToken(Long memberId) {
        Date now = new Date();
        Date validity = new Date(now.getTime() + VALIDATION_INTERVAL);

        return Jwts.builder()
            .claim("memberId", memberId)
            .setIssuedAt(now)
            .setExpiration(validity)
            .signWith(SignatureAlgorithm.HS256, SECRET_KEY)
            .compact();
    }

    public Long getMemberId(String token) {
        return getClaims(token).get("memberId", Long.class);
    }

    private Claims getClaims(String token) {
        try {
            Jws<Claims> claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return claims.getBody();
        } catch (MalformedJwtException | SignatureException | UnsupportedJwtException | IllegalArgumentException e) {
            throw new InvalidTokenException("유효하지 않은 토큰");
        } catch (ExpiredJwtException e) {
            throw new InvalidTokenException("만료된 토큰.");
        }
    }

}
