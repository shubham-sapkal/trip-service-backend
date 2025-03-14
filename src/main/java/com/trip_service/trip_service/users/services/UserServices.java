package com.trip_service.trip_service.users.services;

import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServices {

    @Autowired
    private UserRepository userRepository;

    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(String userName) {
        return userRepository.findById(userName);
    }

    public String loginUser(String username, String password) {
        Optional<Users> foundUser = getUserById(username);

        if( foundUser.isPresent() ) {

            Users users = foundUser.get();

            if( users.getPassword().equals(password) ) {

                // TODO: generate Auth Token

                return "asdasdsa";
            }

            return null;

        }

        return null;
    }


    public Users createUser(Users user) {
        return userRepository.save(user);
    }

    public List<String> manipulateRole( String changeType, String username, String role) {

        List<String> result = null;

        Optional<Users> updateduser = userRepository.findById(username).map( users -> {
            if(changeType.equals("ADD"))
                users.addRole(role);
            else if(changeType.equals("REMOVE"))
                users.removeRole(role);

            return userRepository.save(users);
        });

        if(updateduser.isPresent()) {
            return updateduser.get().getRole();
        }

        return new ArrayList<>();

    }

}
