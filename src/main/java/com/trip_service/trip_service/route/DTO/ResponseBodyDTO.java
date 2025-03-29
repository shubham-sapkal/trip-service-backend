package com.trip_service.trip_service.route.DTO;

import com.trip_service.trip_service.route.models.Route;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

public class ResponseBodyDTO {

    // Trip Details -> created User Response

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class TripDetailsCreatedByUserResponse {
        private String userName;
        private String firstName;
        private String lastName;
        private String email;
        private String phoneNo;
        private String bookingStationAddress;
        private String address;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class tripDetailsVehicleDetailsResponse {
        private String registrationNumber;
        private TripDetailsCreatedByUserResponse vehicleOwner;
        private String seatQuantity;
        private Double pricePerKm;
    }

    @Getter
    @Setter
    @AllArgsConstructor
    @NoArgsConstructor
    static public class TripDetailsResponse {
        private UUID tripId;
        private TripDetailsCreatedByUserResponse createdBy;
        private tripDetailsVehicleDetailsResponse vehicleDetails;
        private Route tripRoute;
        private TripDetailsCreatedByUserResponse driver;
    }

}
