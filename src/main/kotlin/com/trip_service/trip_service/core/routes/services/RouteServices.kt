package com.trip_service.trip_service.core.routes.services

import com.trip_service.trip_service.core.routes.DTO.RoutesReqBody
import com.trip_service.trip_service.core.routes.models.RouteStops
import com.trip_service.trip_service.core.routes.models.Routes
import com.trip_service.trip_service.core.routes.repositories.RouteRepository
import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.users.services.UserService
import jakarta.persistence.Column
import jakarta.persistence.ElementCollection
import jakarta.persistence.ManyToOne
import org.apache.catalina.User
import org.springframework.stereotype.Service

@Service
class RouteServices(
    val routeRepository: RouteRepository,
    val userService: UserService
) {

    /*
    * Service Fun to create Route
    */
    fun createRoute( route: RoutesReqBody.createRoutes ): String {

        // Get User
        val createdBy: Users = userService.getUserById(route.createdBy)

        println("Total Distance: " + route.totalDistance);

        // Create Route Object
        val newRoute = Routes(
            routeName=route.routeName,
            routeOrigin=route.routeOrigin,
            routeDestination=route.routeDestination,
            totalDistance=route.totalDistance.toDouble(),
            routeStops=route.routeStops,
            createdBy=createdBy
        )

        routeRepository.save(newRoute);

        return "Route Created Successfully!"
    }


    /*
    * Service Fun to get All Routes
    */
    fun getAllRoutes(): List<Routes> {
        return routeRepository.findAll();
    }


    /*
    * Service Fun to get route By Id
    */

}