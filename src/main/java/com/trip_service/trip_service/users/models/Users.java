package com.trip_service.trip_service.users.models;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Entity
@Table(name = "users")
public class Users {

    @Id
    @Column(nullable = false, unique = true)
    private String userName;

    @Column(nullable = false)
    private String firstName;

    @Column(nullable = false)
    private String lastName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Column(nullable = false)
    private String phoneNo;

    @Column
    private String address;

    @Column(nullable = false)
    private String bookingStationAddress;

    @Column(nullable = false)
    private String bookingStationPincode;

    @Column
    private List<String> role;

    public List<String> addRole(String newRole) {

        if(role == null) {
            role = new ArrayList<>();
            role.add(newRole);
        }

        if (!role.contains(newRole) ) {
            role.add(newRole);
        }

        return role;
    }

    public List<String> removeRole(String removeRole) {
        role.remove(removeRole);
        return role;
    }
}
