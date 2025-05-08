package com.trip_service.trip_service.core.users.DTO

class RequestBodyDTO {

    // Request Body For ->
    class LoginUser(
        val username: String,
        val password: String
    )

    // Request Body For ->
    class ManipulateRole(
        var changeType: String,
        var username: String,
        var role: String
    )

}