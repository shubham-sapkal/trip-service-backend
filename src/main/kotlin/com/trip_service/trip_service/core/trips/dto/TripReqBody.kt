package com.trip_service.trip_service.core.trips.dto

import java.util.UUID

class TripReqBody {

    class CreateTrip(
        val tripName: String,
        val tripDescription: String,
        val perPersonCost: Double,
        val createdBy: String,
        val driver: String,
        val tripVehicleId: UUID,
        val tripRouteId: UUID,
        val tripStartDate: String,
        val tripEndDate: String,
        val isReturnTrip: Boolean?,
        val isAcAvailable: Boolean?
    )

}