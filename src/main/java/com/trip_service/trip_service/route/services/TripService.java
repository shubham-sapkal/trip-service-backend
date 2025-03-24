package com.trip_service.trip_service.route.services;

import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.route.models.Route;
import com.trip_service.trip_service.route.models.Trip;
import com.trip_service.trip_service.route.models.VehicleDetails;
import com.trip_service.trip_service.route.repositories.RouteRepository;
import com.trip_service.trip_service.route.repositories.TripRepository;
import com.trip_service.trip_service.route.repositories.VehicleDetailsRepository;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Service
public class TripService {

    @Autowired
    private TripRepository tripRepository;

    @Autowired
    private RouteRepository routeRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;

    // Service Method to Find Route Based on Route Name
    public Optional<Route> findRoute(String routeName) {
        return routeRepository.findByRouteName(routeName);
    }


    // Service Method to create Route
    public Route createRoute(Route route) {

        // Check if Route Exists or not
        Optional<Route> foundRoute = findRoute(route.getRouteName());

        if (foundRoute.isPresent()) {
            throw new RuntimeException("Route Exists!!");
        }


        return routeRepository.save( route );

    }

    public List<Route> getAllRoutes()  {
        return routeRepository.findAll();
    }


    //Service Method to create trip
    public Trip createTrip( RequestBodyDTO.TripCreationRequestBody newTrip ) {

        Trip createdTrip = new Trip();

        // Retrived Created By
        Optional<Users> optionalCreatedBy = userRepository.findById(newTrip.getCreatedBy());

        if ( optionalCreatedBy.isEmpty() ) {
            throw new RuntimeException("User Not Found");
        }

        createdTrip.setCreatedBy(optionalCreatedBy.get() );

        // Retrived Vehicle Information
        Optional<VehicleDetails> optionalVehicleDetails = vehicleDetailsRepository.findById(newTrip.getVehicleDetails());

        if(optionalVehicleDetails.isEmpty()) {
            throw new RuntimeException("Vehicle Details Not Found!!");
        }

        createdTrip.setVehicleDetails(optionalVehicleDetails.get());


        // Retrived Trip Information
        Optional<Route> optionalTripRoute = routeRepository.findById(newTrip.getTripRoute());

        if (optionalTripRoute.isEmpty()) {
            throw new RuntimeException("Trip Route Not Found!!");
        }


        createdTrip.setTripRoute(optionalTripRoute.get());

        // Retrived Driver Information
        Optional<Users> optionalDriver = userRepository.findById(newTrip.getDriver());

        if(optionalDriver.isEmpty()) {
            throw new RuntimeException("Driver Not Found!!");
        }

        createdTrip.setDriver(optionalDriver.get());

        createdTrip.setTripStartDate(LocalDateTime.parse(newTrip.getTripStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        createdTrip.setTripEndDate(LocalDateTime.parse(newTrip.getTripEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        createdTrip.setReturnTrip(newTrip.getIsReturnTrip());

        return tripRepository.save( createdTrip );
    }

    // API Service Method to get All Trip Details
    public List<Trip> getAllTrips() {
        return tripRepository.findAll();
    }

}
