package com.munaf.ERP_SYSTEM.configs.security;

import com.munaf.ERP_SYSTEM.entities.User;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Service
public class JwtService {

    @Value("${jwtKey}")
    private String jwtKey;

    private SecretKey getSecretKey() {
        return Keys.hmacShaKeyFor(jwtKey.getBytes(StandardCharsets.UTF_8));
    }

    // TAKES USER ENTITY AND GENERATE JWT TOKEN
    public String generateJwtToken(User user) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("userEmail", user.getEmail())
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000*60*30)) // expire after 30 min
                .signWith(getSecretKey())
                .compact();

    }

    // TAKES JWT TOKEN AND RETURN USER ID
    public Long getUserIdFromJwtToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        return Long.valueOf(claims.getSubject()); // subject contains id in string format
    }

    // GET USER DETAILS FROM TOKEN
    public Map<String, Object> getUserDetailsFromToken(String jwtToken) {
        Claims claims = Jwts.parser()
                .verifyWith(getSecretKey())
                .build()
                .parseSignedClaims(jwtToken)
                .getPayload();

        Long userId = Long.valueOf(claims.getSubject());
        String userEmail = claims.get("userEmail", String.class);

        HashMap<String, Object> userDetails = new HashMap<>();
        userDetails.put("userId", userId);
        userDetails.put("userEmail", userEmail);

        return userDetails;
    }
}
