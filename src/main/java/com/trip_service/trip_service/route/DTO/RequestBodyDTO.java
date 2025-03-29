package com.trip_service.trip_service.route.DTO;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

public class RequestBodyDTO {

    @Getter
    @Setter
    static public class registerVehicleRequestBody {
        private String registrationNumber;
        private String ownerUserName;
        private String noOfSeats;
        private Double pricePerKm;
    }

    @Getter
    @Setter
    static public class TripCreationRequestBody {
        private String createdBy;
        private UUID vehicleDetails;
        private UUID tripRoute;
        private String driver;
        private String tripStartDate;
        private String tripEndDate;
        private Boolean isReturnTrip;
    }

}