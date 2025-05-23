package com.trip_service.trip_service.core.routes.services

import com.trip_service.trip_service.core.routes.DTO.RoutesReqBody
import com.trip_service.trip_service.core.routes.models.RouteStops
import com.trip_service.trip_service.core.routes.models.Routes
import com.trip_service.trip_service.core.routes.repositories.RouteRepository
import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.users.services.UserService
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.dao.DataIntegrityViolationException
import org.springframework.stereotype.Service
import java.util.Optional
import java.util.UUID

@Service
class RouteServices(
    val routeRepository: RouteRepository,
    val userService: UserService
) {

    /*
    * Service Fun to create Route
    */
    fun createRoute( route: RoutesReqBody.createRoutes ): String {

        try{

            // Get User
            val createdBy: Users = userService.getUserById(route.createdBy)

            // Create Route Object
            val newRoute = Routes(
                routeName=route.routeName,
                routeOrigin=route.routeOrigin,
                routeDestination=route.routeDestination,
                totalDistance=route.totalDistance,
                routeStops=route.routeStops,
                createdBy=createdBy
            )

            routeRepository.save(newRoute);

            return "Route Created Successfully!"
        }
        catch (e: DataIntegrityViolationException) {   // TODO: Check proper Exception here
            throw GenerateApiException(
                204,
                "Route Already Exists!"
            )
        }
        catch (e: Exception){
            throw GenerateApiException(
                500,
                e.message ?: "Internal Server Error!"
            )
        }

    }


    /*
    * Service Fun to get All Routes
    */
    fun getAllRoutes(): List<Routes> {
        return routeRepository.findAll();
    }


    /*
    * Service Fun to get route By ID
    */
    fun getRouteById( routeId: UUID ): Optional<Routes> {
        return routeRepository.findById(routeId)
    }

}