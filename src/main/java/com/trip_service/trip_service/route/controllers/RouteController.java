package com.trip_service.trip_service.route.controllers;

import com.trip_service.trip_service.route.models.Route;
import com.trip_service.trip_service.route.services.TripService;
import com.trip_service.trip_service.utils.DTO.SendResponse;
import com.trip_service.trip_service.utils.exceptions.GeneratedApiException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
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
    @PreAuthorize("hasAnyAuthority('ADMIN', 'TRIP_ADMIN', 'ROUTE_ADMIN' )")
    public SendResponse<Route> createRoute(@RequestBody Route route) {

        try {
            Route newRoute =  tripService.createRoute(route);
            return new SendResponse<>(200, "Route Created Successfully!!", "", newRoute);
        }
        catch (GeneratedApiException apiException){
            return new SendResponse<>(apiException.getStatusCode(), apiException.getMessage(), apiException.getErrorMsg(), null);
        }
        catch (Exception e) {
            return new SendResponse<>(500, "Internal Server Error!!", e.getMessage(), null);
        }

    }

    // API View to get All Route
    @PostMapping("/all")
    @PreAuthorize("hasAnyAuthority('ADMIN')")
    public SendResponse<List<Route>> getAllRoutes() {
        return new SendResponse<>(200, "Data Retrieved Successfully!", "",tripService.getAllRoutes());
    }

}
