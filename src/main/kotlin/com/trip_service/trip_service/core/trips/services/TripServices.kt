package com.trip_service.trip_service.core.trips.services

import com.trip_service.trip_service.core.routes.services.RouteServices
import com.trip_service.trip_service.core.trips.dto.TripReqBody
import com.trip_service.trip_service.core.trips.models.TripDetails
import com.trip_service.trip_service.core.trips.models.TripStatus
import com.trip_service.trip_service.core.trips.repositories.TripDetailsRepository
import com.trip_service.trip_service.core.users.services.UserService
import com.trip_service.trip_service.core.vehicles.services.VehicleServices
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.stereotype.Service
import java.time.LocalDateTime
import java.time.format.DateTimeFormatter
import java.util.Optional
import java.util.UUID

@Service
class TripServices(
    val tripDetailsRepository: TripDetailsRepository,
    val userService: UserService,
    val vehicleServices: VehicleServices,
    val routeServices: RouteServices
) {

    /*
    *  Service Function: Create Trip
    */
    fun createTrip(tripDetails: TripReqBody.CreateTrip): String {

        // Get CreatedBy User
        val createdBy = userService.getUserById(tripDetails.createdBy)

        // Get driver User
        val driver = userService.getUserById(tripDetails.driver)

        // Get Trip Vehicle
        val tripVehicle = vehicleServices.getVehicleById(tripDetails.tripVehicleId)

        if(tripVehicle.isEmpty) {
            throw GenerateApiException(404, "Vehicle Not Found")
        }

        // Get Trip Routes
        val tripRoute = routeServices.getRouteById(tripDetails.tripRouteId)

        if( tripRoute.isEmpty ) {
            throw GenerateApiException(404, "Route Not Found")
        }

        // TODO: Validation: to check if same name, name created by trip is available or not


        // TODO: Validation: Check drivers availability for that time frame.


        val newTrip = TripDetails(
            tripName = tripDetails.tripName,
            tripDescription = tripDetails.tripDescription,
            perPersonCost = tripDetails.perPersonCost,
            createdBy = createdBy,
            driver = driver,
            tripVehicle = tripVehicle.get(),
            tripRoutes = tripRoute.get(),
            tripStatus = TripStatus.PLANNED,
            tripStartDate = LocalDateTime.parse(tripDetails.tripStartDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            tripEndDate = LocalDateTime.parse(tripDetails.tripEndDate, DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
            isReturnTrip = tripDetails.isReturnTrip ?: false,
            isAcAvailable = tripDetails.isAcAvailable ?: false
        )

        tripDetailsRepository.save(newTrip)

        return "Trip Created Successfully"
    }


    /*
    * Service Function: Get All Trip Details
    */
    fun getAllTrips(): List<TripDetails> {
        return tripDetailsRepository.findAll()
    }

    /*
    * Service Function: Get Trip Details By Id
    */
    fun getTripDetailsById(tripId: UUID): Optional<TripDetails> {
        return tripDetailsRepository.findById(tripId)
    }

}