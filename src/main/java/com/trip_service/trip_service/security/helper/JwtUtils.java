package com.trip_service.trip_service.security.helper;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;


@Component
public class JwtUtils {

    // Token Expiry time
    public static final long JWT_TOKEN_VALIDITY = 5 * 60 * 60;

    // Token Secret Key
    @Value("${spring.security.jwt_signing_key}")
    private String secret;

    // Claim Functionality
    private Claims getAllClaimsFromToken(String token) {
        return Jwts
                .parser()
                .setSigningKey(secret.getBytes())
                .parseClaimsJws(token)
                .getBody();
    }

    public <T> T getClaimFromToken(String token, Function<Claims, T>  claimsResolver) {
        final Claims claims = getAllClaimsFromToken(token);
        return claimsResolver.apply(claims);
    }


    // Util Function to Extract username from JWT token
    public String getUsernameFromToken(String token) {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Util Function to Expiration Date from Token
    public Date getExpirationDateFromToken(String token) {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Util Function to Check if token is expired
    private Boolean isTokenExpired(String token) {
        final Date expiration = getExpirationDateFromToken(token);
        return expiration.before(new Date());
    }

    // Generate Token for User
    public String generateToken(DTOEntity.TokenBodyDTO tokenBody) {
        Map<String, Object> claims = new HashMap<>();

        claims.put("username", tokenBody.getUsername());
        claims.put("email", tokenBody.getEmail());
        claims.put("roles", tokenBody.getRoles());

        return doGenerateToken(claims, tokenBody.getUsername());
    }

    // Creating the token -
    //  1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //  2. Sign the JWT using the HS512 algorithm and secret key.
    //  3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private String doGenerateToken(Map<String, Object> claims, String subject ) {

        return Jwts
                .builder()
                .setClaims(claims)
                .setSubject(subject)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000 ))
                .signWith(SignatureAlgorithm.HS512, secret.getBytes())
                .compact();
    }

    // Validate Token
    public Boolean validateToken(String token, DTOEntity.TokenBodyDTO tokenBody) {
        final String username = getUsernameFromToken(token);
        return (username.equals(tokenBody.getUsername()) && !isTokenExpired(token));
    }


}
