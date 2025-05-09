package com.trip_service.trip_service.security.DTO

class DTO {

    // DTO For -> JwtUtils.kt / generateToken
    class GenerateToken(
        val username: String,
        val email: String,
        val roles: Set<String>
    )

}