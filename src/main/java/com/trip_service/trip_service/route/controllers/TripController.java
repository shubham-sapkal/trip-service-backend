package com.trip_service.trip_service.route.controllers;

import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.route.DTO.ResponseBodyDTO;
import com.trip_service.trip_service.route.models.Trip;
import com.trip_service.trip_service.route.services.TripService;
import com.trip_service.trip_service.utils.DTO.SendResponse;
import com.trip_service.trip_service.utils.exceptions.GeneratedApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/trip")
public class TripController {

    @Autowired
    private TripService tripService;

    @GetMapping
    public String checkTripService() {
        return "<h1> Trip Service is up and running </h1>";
    }


    // API View to create Trip
    @PostMapping("/create")
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIP_ADMIN', 'TRIP_CREATER') ")
    public SendResponse<Trip> createTrip(@RequestBody RequestBodyDTO.TripCreationRequestBody newTrip ) {

        try {
            return new SendResponse<>(200, "Trip Created Successfully!", "", tripService.createTrip(newTrip));
        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, "Internal Server Error!!", e.getMessage(), null);
        }

    }

    // API View to get all trips
    @GetMapping("/all")
    public SendResponse<List<ResponseBodyDTO.TripDetailsResponse>> getAllTrips() {
        try {
            return new SendResponse<>(200, "Data Retrieved Successfully!!", "",tripService.getAllTrips());
        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, "Internal Server Error!!", e.getMessage(), null);
        }
    }

    @PostMapping("/trip-details/{tripId}")
    public SendResponse<ResponseBodyDTO.TripDetailsResponse> getTripById(@PathVariable UUID tripId ) {

        try {
            return new SendResponse<>(200, "Trip Found Successfully!","", tripService.getTripById(tripId));
        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, "Internal Server Error!!", e.getMessage(), null);
        }

    }

}
