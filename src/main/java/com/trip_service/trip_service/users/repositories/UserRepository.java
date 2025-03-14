package com.trip_service.trip_service.users.repositories;

import com.trip_service.trip_service.users.models.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<Users, String> {

}
