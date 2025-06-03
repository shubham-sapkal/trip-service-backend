package com.trip_service.trip_service.core.vehicles.controllers

import com.trip_service.trip_service.core.vehicles.dto.VehiclesRequestBody
import com.trip_service.trip_service.core.vehicles.models.Vehicles
import com.trip_service.trip_service.core.vehicles.services.VehicleServices
import com.trip_service.trip_service.helpers.dto.GenerateResponse
import com.trip_service.trip_service.helpers.errors.GenerateApiException
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/vehicles")
class VehicleController {

    @Autowired
    private lateinit var vehicleServices: VehicleServices;

    /*
    Create Vehicle
    */
    @PostMapping("/register")
    fun createVehicle(@RequestBody vehicle: VehiclesRequestBody.CreateVehicles): GenerateResponse<String> {

        try {

            vehicleServices.createNewVehicle(vehicle);

            return GenerateResponse(200, "Vehicle Created Successfully!", "", null);

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
    Get All  Vehicle Details
     */
    @PostMapping("/all")
    fun getAllVehicles(): GenerateResponse<List<Vehicles>> {

        try {

            return GenerateResponse(
                200,
                "Vehicle Retrived Successfully!!",
                "",
                vehicleServices.getAllVehicles()
            );

        }
        catch (exception: GenerateApiException) {
            return GenerateResponse(
                exception.status,
                "",
                exception.errorMessage,
                null
            );
        }

    }

}