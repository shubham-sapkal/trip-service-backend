package com.trip_service.trip_service.route.repositories;

import com.trip_service.trip_service.route.models.Route;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface RouteRepository extends JpaRepository<Route, UUID> {

    Optional<Route> findByRouteName(String routeName);

}
