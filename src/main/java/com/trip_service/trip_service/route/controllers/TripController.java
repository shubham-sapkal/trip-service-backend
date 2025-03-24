package com.trip_service.trip_service.route.controllers;

import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.route.models.Trip;
import com.trip_service.trip_service.route.services.TripService;
import com.trip_service.trip_service.utils.DTO.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    public SendResponse<Trip> createTrip(@RequestBody RequestBodyDTO.TripCreationRequestBody newTrip ) {

        try {

            return new SendResponse<>(200, "Trip Created Successfully!", tripService.createTrip(newTrip));

        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }

    }

    // API View to get all trips
    @PostMapping("/all")
    public SendResponse<List<Trip>> getAllTrips() {
        try {
            return new SendResponse<>(200, "Data Retrived Successfully!!", tripService.getAllTrips());
        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }
    }


}
