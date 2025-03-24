package com.trip_service.trip_service.route.controllers;

import com.trip_service.trip_service.route.models.VehicleDetails;
import com.trip_service.trip_service.route.services.VehicleService;
import com.trip_service.trip_service.route.DTO.RequestBodyDTO;
import com.trip_service.trip_service.utils.DTO.SendResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/vehicle")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;

    @PostMapping("/register")
    public SendResponse<VehicleDetails> registerVehicle(@RequestBody RequestBodyDTO.registerVehicleRequestBody requestBody) {

        try {
            return new SendResponse<>(
                    200,
                    "Vehicle Registered Successfully",
                    vehicleService.registerVehicle(requestBody)
            );
        }
        catch (Exception e) {
            return new SendResponse<>(
                    500,
                    e.getMessage(),
                    null
            );
        }

    }


}
