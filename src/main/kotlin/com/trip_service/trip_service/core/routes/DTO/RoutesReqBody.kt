package com.trip_service.trip_service.core.routes.DTO

import com.trip_service.trip_service.core.routes.models.RouteStops

class RoutesReqBody {

    // Request Body for -> Creating Routes
    class createRoutes(
        val routeName: String,
        val routeOrigin: RouteStops,
        val routeDestination: RouteStops,
        val totalDistance: Double,
        val routeStops: MutableList<RouteStops>,
        val createdBy: String,
    )

}