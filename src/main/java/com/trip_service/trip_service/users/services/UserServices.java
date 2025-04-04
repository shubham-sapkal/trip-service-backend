package com.trip_service.trip_service.users.services;

import com.trip_service.trip_service.security.helper.DTOEntity;
import com.trip_service.trip_service.security.helper.JwtUtils;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.repositories.UserRepository;
import com.trip_service.trip_service.utils.exceptions.GeneratedApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices implements UserDetailsService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private JwtUtils jwtUtils;

    // User Details Service Implementation

    private Set<SimpleGrantedAuthority> getAuthority(Users user) {
        Set<SimpleGrantedAuthority> authorities = new HashSet<>();

        user.getRole().forEach(role -> {
            authorities.add(new SimpleGrantedAuthority(role));
        });

        return authorities;
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        Optional<Users> optionalUser = getUserById(username);

        if ( optionalUser.isEmpty() ) {
            throw new UsernameNotFoundException("Invalid User!!");
        }

        Users user = optionalUser.get();

        return new User(
                user.getUserName(),
                user.getPassword(),
                getAuthority(user)
        );
    }

    public List<Users> getAllUser() {
        return userRepository.findAll();
    }

    public Optional<Users> getUserById(String userName) {
        return userRepository.findById(userName);
    }

    public String loginUser(String username, String password) {
        Optional<Users> foundUser = getUserById(username);

        if(foundUser.isEmpty()) {
            throw new GeneratedApiException(204, "User not Found!!");
        }

        Users users = foundUser.get();

        if( users.getPassword().equals(password) ) {

            // generate Auth Token
            return this.jwtUtils.generateToken(new DTOEntity.TokenBodyDTO(
                    users.getUserName(),
                    users.getEmail(),
                    users.getRole()
            ));
        }
        else {
            throw new GeneratedApiException(204, "User and Password not matched!! ");
        }

    }


    public Users createUser(Users user) {

        // Check if User is already Exist
        Optional<Users> optionalUser = getUserById(user.getUserName());

        if(optionalUser.isPresent()) {
            throw new GeneratedApiException(204, "User Already Exists!!" );
        }

        return userRepository.save(user);
    }

    public List<String> manipulateRole( String changeType, String username, String role) {


        Optional<Users> updatedUser = userRepository.findById(username).map( users -> {
            if(changeType.equals("ADD"))
                users.addRole(role);
            else if(changeType.equals("REMOVE"))
                users.removeRole(role);

            return userRepository.save(users);
        });

        if(updatedUser.isPresent()) {
            return updatedUser.get().getRole();
        }

        return new ArrayList<>();

    }

}
