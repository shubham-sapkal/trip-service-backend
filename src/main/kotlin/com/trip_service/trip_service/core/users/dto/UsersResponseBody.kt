package com.trip_service.trip_service.core.users.dto

class UsersResponseBody {

    // Response Body for user info
    class UserInfo (
        var username: String,
        var firstName: String,
        var lastName: String,
        var email: String,
        var phoneNo: String,
        var roles: Set<String>
    )

}