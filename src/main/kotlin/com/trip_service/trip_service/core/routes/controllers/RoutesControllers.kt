package com.trip_service.trip_service.core.routes.controllers

import com.trip_service.trip_service.core.routes.dto.RoutesReqBody
import com.trip_service.trip_service.core.routes.models.Routes
import com.trip_service.trip_service.core.routes.services.RouteServices
import com.trip_service.trip_service.helpers.dto.GenerateResponse
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController
import java.util.UUID

@RestController
@RequestMapping("/routes")
class RoutesControllers(
    val routeServices: RouteServices
) {

    @PostMapping("/create")
    fun createRoute(@RequestBody route: RoutesReqBody.CreateRoutes): GenerateResponse<String> {

        try {

            return GenerateResponse(
                200,
                routeServices.createRoute(route),
                "",
                null
            )

        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage,
                null
            )
        }

    }

    @GetMapping("/all")
    fun getAllRoutes(): GenerateResponse<List<Routes>> {
        try {
            return GenerateResponse(
                200,
                "Routes Fetch Successfully!",
                "",
                routeServices.getAllRoutes()
            )
        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage,
                null
            )
        }
    }

    @GetMapping("/{routeId}")
    fun getRouteById( @PathVariable routeId: UUID): GenerateResponse<Routes> {

        val optionalRoute = routeServices.getRouteById(routeId)

        if (optionalRoute.isEmpty) {
            throw GenerateApiException(404, "Route Not Found!")
        }

        try {
            return GenerateResponse(
                200,
                "Route Fetch Successfully!",
                "",
                optionalRoute.get()
            )
        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage,
                null
            )
        }
    }

}