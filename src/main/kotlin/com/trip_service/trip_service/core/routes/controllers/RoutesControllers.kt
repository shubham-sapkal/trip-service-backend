package com.trip_service.trip_service.core.routes.controllers

import com.trip_service.trip_service.core.routes.DTO.RoutesReqBody
import com.trip_service.trip_service.core.routes.models.Routes
import com.trip_service.trip_service.core.routes.services.RouteServices
import com.trip_service.trip_service.helpers.DTO.GenerateResponse
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/routes")
class RoutesControllers(
    val routeServices: RouteServices
) {

    @PostMapping("/create")
    fun createRoute(route: RoutesReqBody.createRoutes): GenerateResponse<String> {

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

}