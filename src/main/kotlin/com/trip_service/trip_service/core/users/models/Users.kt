package com.trip_service.trip_service.core.users.models


import com.trip_service.trip_service.core.users.dto.UsersResponseBody
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime


@Entity
@Table(name = "users", schema = "public")
class Users(

    @Id
    @Column(nullable = false, unique = true)
    var username: String,

    @Column(nullable = false)
    var firstName: String,

    @Column(nullable = false)
    var lastName: String,

    @Column(nullable = false, unique = true)
    var email: String,

    @Column(nullable = false)
    var password: String,

    @Column
    var phoneNo: String,

    @Column
    var roles: Set<String>,

    @Column
    var created_at: LocalDateTime = LocalDateTime.now()

) {

    fun addRole(newRole: String) {

        if (roles == null) {
            roles = HashSet<String>();
        }

        (roles as HashSet<String>).add(newRole);

    }

    fun removeRole(removeRole: String) {
        (roles as HashSet<String>).remove(removeRole);
    }

    fun getFilteredData(): UsersResponseBody.UserInfo {
        return UsersResponseBody.UserInfo(
            username = this.username,
            firstName = this.firstName,
            lastName = this.lastName,
            email = this.email,
            phoneNo = this.phoneNo,
            roles = this.roles
        )
    }

}