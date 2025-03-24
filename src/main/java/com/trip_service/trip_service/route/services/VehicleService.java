package com.trip_service.trip_service.route.services;

import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.route.models.VehicleDetails;
import com.trip_service.trip_service.route.repositories.VehicleDetailsRepository;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleDetailsRepository vehicleDetailsRepository;
    @Autowired
    private UserRepository userRepository;

    public VehicleDetails registerVehicle(RequestBodyDTO.registerVehicleRequestBody vehicleDetails) {

        // Check if Vehicle is Already Exist
        if(vehicleDetailsRepository.existsByRegistrationNumber(vehicleDetails.getRegistrationNumber())) {
            throw new RuntimeException("Vehicle Already Exists!");
        }

        // Fetch User by userName
        Optional<Users> user = userRepository.findById(vehicleDetails.getOwnerUserName());

        if( user.isEmpty() ) {
            throw new RuntimeException("User not found!");
        }

        Users actualUser = user.get();

        // Create Vehicle Details
        VehicleDetails savedVehicleDetail = new VehicleDetails(
          vehicleDetails.getRegistrationNumber(),
          actualUser,
          vehicleDetails.getNoOfSeats(),
          vehicleDetails.getPricePerKm()
        );

        return vehicleDetailsRepository.save(savedVehicleDetail);

    }

    public List<VehicleDetails> getAllVehicleDetails() {
        return vehicleDetailsRepository.findAll();
    }


}
