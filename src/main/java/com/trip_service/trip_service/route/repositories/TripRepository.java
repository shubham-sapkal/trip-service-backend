package com.trip_service.trip_service.route.repositories;

import com.trip_service.trip_service.route.models.Trip;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface TripRepository extends JpaRepository<Trip, UUID> {
}
