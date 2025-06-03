package com.trip_service.trip_service.core.trips.controllers

import com.trip_service.trip_service.core.trips.dto.TripReqBody
import com.trip_service.trip_service.core.trips.models.TripDetails
import com.trip_service.trip_service.core.trips.services.TripServices
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
@RequestMapping("/trips")
class TripsController(
    val tripServices : TripServices
) {

    /*
    * Controller For: Creating Trip
    */
    @PostMapping("/create")
    fun createTrip(@RequestBody tripDetails: TripReqBody.CreateTrip): GenerateResponse<String> {

        try{

            return GenerateResponse(
                200,
                tripServices.createTrip(tripDetails),
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

    /*
    * Controller For: Get All Trips
    */
    @PostMapping("/all")
    fun getAllTrips(): GenerateResponse<List<TripDetails>> {

        try{

            return GenerateResponse(
                200,
                "Trip Fetch Successfully!",
                "",
                tripServices.getAllTrips()
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

    /*
    * Controller For:Get Trip Details By ID
    */
    @GetMapping("/{tripId}")
    fun getTripById(@PathVariable tripId: UUID ): GenerateResponse<TripDetails> {

        val tripDetails = tripServices.getTripDetailsById(tripId)

        if ( tripDetails.isEmpty ) {
            return GenerateResponse(
                404,
                "",
                "Trip Not Found",
                null
            )
        }

        try {
            return GenerateResponse(
                200,
                "Trip Fetch Successfully!",
                "",
                tripDetails.get()
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