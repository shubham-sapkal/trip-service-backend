package com.trip_service.trip_service.core.users.services

import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.users.repositories.UserRepository
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.util.*

@Service
class UserService {

    @Autowired
    private lateinit var userRepository: UserRepository;

    // Service Function for creating new User
    fun createUser(user: Users): String {

        val foundUser: Optional<Users> = getUserById(user.username)

        if(foundUser.isPresent) {
            throw GenerateApiException(409, "User Already Exists!!")
        }

        userRepository.save(user)

        return "User is Created!!"

    }

    // Login Users
    fun login(username: String, password: String): String {

        val foundUser: Optional<Users> = getUserById(username)

        if(foundUser.isEmpty) {
            throw GenerateApiException(404, "User Not Found!")
        }

        val users: Users  = foundUser.get();

        if( users.password.equals(password) ) {

            // Generate JWT Auth Token
            return "asdsad"

        }
        else {
            throw GenerateApiException(400, "Password is incorrect!!")
        }

    }

    // Get Users by username
    fun getUserById(username: String): Optional<Users> {
        return userRepository.findById(username);
    }

    // Get All Users
    fun getAllUsers(): List<Users> {
        return userRepository.findAll();
    }

    // Manipulate User Roles
    fun manipulateRole(username: String, role: String, changeType: String ): Set<String> {

        if( !Arrays.asList("ADD", "REMOVE").contains(changeType)){
            throw GenerateApiException(404, "ChangeType Not Found!");
        }

        val optionalUser: Optional<Users> = userRepository.findById(username).map { user ->
            if (changeType == "ADD") {
                user.addRole(role);
            }

            if(changeType == "REMOVE") {
                user.removeRole(role);
            }

            userRepository.save(user);

        }

        if(optionalUser.isEmpty) {
            throw GenerateApiException(404, "User Details Not Found!");
        }

        return optionalUser.get().roles;

    }

}