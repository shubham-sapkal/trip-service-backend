package com.trip_service.trip_service.security.jwt

import com.trip_service.trip_service.security.DTO.DTO
import org.springframework.beans.factory.annotation.Value
import java.util.Date

import io.jsonwebtoken.Jwts
import io.jsonwebtoken.Claims
import io.jsonwebtoken.SignatureAlgorithm
import org.springframework.stereotype.Component

@Component
class JwtUtils(
    @Value("\${spring.security.jwt_signing_key}")
    private val secret: String,

    private val JWT_TOKEN_VALIDITY: Int = 5 * 60 * 60 // 1 Hour
) {

    // Claim Functionality
    private fun getAllClaimsFromToken(token: String): Claims {
        return Jwts
            .parser()
            .setSigningKey(secret.toByteArray())
            .parseClaimsJws(token)
            .getBody();
    }

    fun <T> getClaimFromToken(token: String, claimsResolver: (Claims) -> T): T {
        val claims: Claims = getAllClaimsFromToken(token);
        return claimsResolver(claims);
    }

    // Utility Function to Extract Username From JWT
    fun getUsernameFromToken(token: String): String {
        return getClaimFromToken(token, Claims::getSubject);
    }

    // Utility Function to get Expiry Date From Token
    fun getExpirationDateFromToken(token: String): Date {
        return getClaimFromToken(token, Claims::getExpiration);
    }

    // Utility Function to Check if token is Expire
    private fun isTokenExpired(token: String): Boolean {
        val expiration: Date = getExpirationDateFromToken(token);
        return expiration.before(Date());
    }

    // Generate Token For User
    fun generateToken(tokenBody: DTO.GenerateToken): String {

        var claims = mutableMapOf<String, Any>()

        claims["username"] = tokenBody.username;
        claims["email"] = tokenBody.email;
        claims["roles"] = tokenBody.roles;

        return doGenerateToken(claims, tokenBody.username);

    }

    // Creating the Token -
    //  1. Define  claims of the token, like Issuer, Expiration, Subject, and the ID
    //  2. Sign the JWT using the HS512 algorithm and secret key.
    //  3. According to JWS Compact Serialization(https://tools.ietf.org/html/draft-ietf-jose-json-web-signature-41#section-3.1)
    //   compaction of the JWT to a URL-safe string
    private fun doGenerateToken(claims: Map<String, Any>, subject: String): String {

        return Jwts
            .builder()
            .setClaims(claims)
            .setSubject(subject)
            .setIssuedAt(Date(System.currentTimeMillis()))
            .setExpiration(Date( System.currentTimeMillis() + JWT_TOKEN_VALIDITY * 1000 ))
            .signWith(SignatureAlgorithm.HS512, secret.toByteArray())
            .compact();

    }

    // Validate Token
    fun validateToken(token: String, tokenBody: DTO.GenerateToken): Boolean {
        val username: String = getUsernameFromToken(token);

        return (
                username.equals(tokenBody.username) &&
                !isTokenExpired(token)
                );
    }

}