package com.trip_service.trip_service.route.repositories;

import com.trip_service.trip_service.route.models.VehicleDetails;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface VehicleDetailsRepository extends JpaRepository<VehicleDetails, UUID> {
    boolean existsByRegistrationNumber(String registrationNumber );
}
