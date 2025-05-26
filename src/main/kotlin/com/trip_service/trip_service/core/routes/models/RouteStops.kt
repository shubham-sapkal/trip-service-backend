package com.trip_service.trip_service.core.routes.models

import java.io.Serializable

class RouteStops (
    val stopSequence: Int,
    val locationName: String,
    val cityName: String,
    val pinCode: String,
    val lat: String,
    val long: String
)  : Serializable