package com.trip_service.trip_service.route.controllers;

import com.trip_service.trip_service.route.models.Route;
import com.trip_service.trip_service.route.services.TripService;
import com.trip_service.trip_service.utils.DTO.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/route")
public class RouteController {

    @Autowired
    private TripService tripService;

    // API View to create Route
    @PostMapping("/create")
    public SendResponse<Route> createRoute(@RequestBody Route route) {

        try {
            Route newRoute =  tripService.createRoute(route);
            return new SendResponse<>(200, "Route Created Successfully!!", newRoute);
        }
        catch (Exception e) {
            return new SendResponse<>(500, e.getMessage(), null);
        }

    }

    // API View to get All Route
    @PostMapping("/all")
    public SendResponse<List<Route>> getAllRoutes() {
        return new SendResponse<>(200, "Data Retrieved Successfully!", tripService.getAllRoutes());
    }

}
