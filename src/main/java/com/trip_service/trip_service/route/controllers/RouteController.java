package com.trip_service.trip_service.route.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/route")
public class RouteController {

    @GetMapping
    public String checkRouteService() {
        return "<h1> Route Service is up and running </h1>";
    }

}
