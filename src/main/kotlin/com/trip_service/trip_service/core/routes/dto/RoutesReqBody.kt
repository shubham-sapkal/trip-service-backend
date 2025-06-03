package com.trip_service.trip_service.core.routes.dto

import com.trip_service.trip_service.core.routes.models.RouteStops

class RoutesReqBody {

    // Request Body for -> Creating Routes
    class CreateRoutes(
        val routeName: String,
        val routeOrigin: RouteStops,
        val routeDestination: RouteStops,
        val totalDistance: Double,
        val routeStops: MutableList<RouteStops>,
        val createdBy: String,
    )

}