package com.trip_service.trip_service.core.vehicles.dto


class VehiclesRequestBody {

    // Request Body ->  for Creating Vehicles
    class CreateVehicles(
        val vehicleRegNo: String,
        val vehicleOwner: String,
        val totalSeats: Int,
        val pricePerKm: Double,
        val fuelType: String,
        val isACAvailable: Boolean?,
    )

}