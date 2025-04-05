package com.trip_service.trip_service.users.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
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

    public void addRole(String newRole) {

        if(role == null) {
            role = new ArrayList<>();
            role.add(newRole);
        }

        if (!role.contains(newRole) ) {
            role.add(newRole);
        }

    }

    public void removeRole(String removeRole) {
        role.remove(removeRole);
    }
}
