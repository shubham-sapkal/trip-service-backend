package com.trip_service.trip_service.route.services;

import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.route.DTO.ResponseBodyDTO;
import com.trip_service.trip_service.route.models.Route;
import com.trip_service.trip_service.route.models.Trip;
import com.trip_service.trip_service.route.models.VehicleDetails;
import com.trip_service.trip_service.route.repositories.RouteRepository;
import com.trip_service.trip_service.route.repositories.TripRepository;
import com.trip_service.trip_service.route.repositories.VehicleDetailsRepository;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.repositories.UserRepository;
import com.trip_service.trip_service.utils.exceptions.GeneratedApiException;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

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
            throw new GeneratedApiException(204, "Route Exists!!");
        }

        return routeRepository.save( route );

    }

    public List<Route> getAllRoutes()  {
        return routeRepository.findAll();
    }


    //Service Method to create trip
    public Trip createTrip( RequestBodyDTO.TripCreationRequestBody newTrip ) {

        Trip createdTrip = new Trip();

        // Retrieved Created By
        Optional<Users> optionalCreatedBy = userRepository.findById(newTrip.getCreatedBy());

        if ( optionalCreatedBy.isEmpty() ) {
            throw new GeneratedApiException(404, "User Not Found");
        }

        createdTrip.setCreatedBy(optionalCreatedBy.get() );

        // Retrieved Vehicle Information
        Optional<VehicleDetails> optionalVehicleDetails = vehicleDetailsRepository.findById(newTrip.getVehicleDetails());

        if(optionalVehicleDetails.isEmpty()) {
            throw new GeneratedApiException(404, "Vehicle Details Not Found!!");
        }

        createdTrip.setVehicleDetails(optionalVehicleDetails.get());


        // Retrieved Trip Information
        Optional<Route> optionalTripRoute = routeRepository.findById(newTrip.getTripRoute());

        if (optionalTripRoute.isEmpty()) {
            throw new GeneratedApiException(404, "Trip Route Not Found!!");
        }


        createdTrip.setTripRoute(optionalTripRoute.get());

        // Retrieved Driver Information
        Optional<Users> optionalDriver = userRepository.findById(newTrip.getDriver());

        if(optionalDriver.isEmpty()) {
            throw new GeneratedApiException(404, "Driver Not Found!!");
        }

        createdTrip.setDriver(optionalDriver.get());

        createdTrip.setTripStartDate(LocalDateTime.parse(newTrip.getTripStartDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));
        createdTrip.setTripEndDate(LocalDateTime.parse(newTrip.getTripEndDate(), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")));

        createdTrip.setReturnTrip(newTrip.getIsReturnTrip());

        return tripRepository.save( createdTrip );
    }

    // API Service Method to get All Trip Details
    public List<ResponseBodyDTO.TripDetailsResponse> getAllTrips() {

        List<Trip> tripList = tripRepository.findAll();

        List<ResponseBodyDTO.TripDetailsResponse> tripDetailsResponse = new ArrayList<>();

        for( Trip trip : tripList ) {

            Users createdByUser = trip.getCreatedBy();
            VehicleDetails vehicleDetails = trip.getVehicleDetails();
            Users vehicleOwnerDetails = vehicleDetails.getVehicleOwner();
            Users driverInfo = trip.getDriver();

            tripDetailsResponse.add(new ResponseBodyDTO.TripDetailsResponse(
                trip.getTripId(),
                new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                        createdByUser.getUserName(),
                        createdByUser.getFirstName(),
                        createdByUser.getLastName(),
                        createdByUser.getEmail(),
                        createdByUser.getPhoneNo(),
                        createdByUser.getBookingStationAddress(),
                        createdByUser.getAddress()
                ),
                new ResponseBodyDTO.tripDetailsVehicleDetailsResponse(
                        vehicleDetails.getRegistrationNumber(),
                        new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                                vehicleOwnerDetails.getUserName(),
                                vehicleOwnerDetails.getFirstName(),
                                vehicleOwnerDetails.getLastName(),
                                vehicleOwnerDetails.getEmail(),
                                vehicleOwnerDetails.getPhoneNo(),
                                vehicleOwnerDetails.getBookingStationAddress(),
                                vehicleOwnerDetails.getAddress()
                        ),
                        vehicleDetails.getSeatQuantity(),
                        vehicleDetails.getPricePerKm()
                ),
                trip.getTripRoute(),
                new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                        driverInfo.getUserName(),
                        driverInfo.getFirstName(),
                        driverInfo.getLastName(),
                        driverInfo.getEmail(),
                        driverInfo.getPhoneNo(),
                        driverInfo.getBookingStationAddress(),
                        driverInfo.getAddress()
                )
            ));

        }

        return tripDetailsResponse;
    }

    // API Service Method to get Trip By id
    public ResponseBodyDTO.TripDetailsResponse getTripById(UUID tripId) {

        Optional<Trip> optionalTrip = tripRepository.findById(tripId);

        if(optionalTrip.isEmpty()) {
            throw new GeneratedApiException(404, "Trip Details Not Found!!");
        }

        Trip trip = optionalTrip.get();

        Users createdByUser = trip.getCreatedBy();
        VehicleDetails vehicleDetails = trip.getVehicleDetails();
        Users vehicleOwnerDetails = vehicleDetails.getVehicleOwner();
        Users driverInfo = trip.getDriver();

        return new ResponseBodyDTO.TripDetailsResponse(
            trip.getTripId(),
            new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                    createdByUser.getUserName(),
                    createdByUser.getFirstName(),
                    createdByUser.getLastName(),
                    createdByUser.getEmail(),
                    createdByUser.getPhoneNo(),
                    createdByUser.getBookingStationAddress(),
                    createdByUser.getAddress()
            ),
            new ResponseBodyDTO.tripDetailsVehicleDetailsResponse(
                    vehicleDetails.getRegistrationNumber(),
                    new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                            vehicleOwnerDetails.getUserName(),
                            vehicleOwnerDetails.getFirstName(),
                            vehicleOwnerDetails.getLastName(),
                            vehicleOwnerDetails.getEmail(),
                            vehicleOwnerDetails.getPhoneNo(),
                            vehicleOwnerDetails.getBookingStationAddress(),
                            vehicleOwnerDetails.getAddress()
                    ),
                    vehicleDetails.getSeatQuantity(),
                    vehicleDetails.getPricePerKm()
            ),
            trip.getTripRoute(),
            new ResponseBodyDTO.TripDetailsCreatedByUserResponse(
                    driverInfo.getUserName(),
                    driverInfo.getFirstName(),
                    driverInfo.getLastName(),
                    driverInfo.getEmail(),
                    driverInfo.getPhoneNo(),
                    driverInfo.getBookingStationAddress(),
                    driverInfo.getAddress()
            )
            );

    }

}
