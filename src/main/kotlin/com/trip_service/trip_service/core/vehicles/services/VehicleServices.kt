package com.trip_service.trip_service.core.vehicles.services


import com.trip_service.trip_service.core.users.services.UserService
import com.trip_service.trip_service.core.vehicles.DTO.VehiclesRequestBody
import com.trip_service.trip_service.core.vehicles.models.FuelType
import com.trip_service.trip_service.core.vehicles.models.Vehicles
import com.trip_service.trip_service.core.vehicles.repositories.VehicleRepository
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service
class VehicleServices {

    @Autowired
    private lateinit var vehicleRepository: VehicleRepository;

    @Autowired
    private lateinit var userServices: UserService;

    fun createNewVehicle(vehicle: VehiclesRequestBody.createVehicles) {

        // TODO: Check if vehicle already exists

        val vehicleOwner = userServices.getUserById(vehicle.vehicleOwner);


        val vehicleRegistery = Vehicles(
            vehicleRegNo = vehicle.vehicleRegNo,
            vehicleOwner = vehicleOwner,
            totalSeats = vehicle.totalSeats,
            pricePerKm = vehicle.pricePerKm,
            fuelType = FuelType.valueOf(vehicle.fuelType),
            isACAvailable = vehicle.isACAvailable ?: false
        )


        vehicleRepository.save(vehicleRegistery);

    }

    fun getAllVehicles(): List<Vehicles> {
        return vehicleRepository.findAll();
    }


}