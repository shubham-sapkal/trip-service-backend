package com.trip_service.trip_service.route.models;

import com.trip_service.trip_service.users.models.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.UUID;

@Entity
@Table(name = "vehicle_details")
@Getter
@Setter
@NoArgsConstructor
public class VehicleDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID vehicleId;

    @Column(nullable = false, unique = true)
    private String registrationNumber;

    // Foreign Key reference to Users table
    @ManyToOne
    @JoinColumn(name = "user_name", nullable = false)
    private Users vehicleOwner;

    @Column(nullable = false)
    private String seatQuantity;

    @Column(nullable = false)
    private Double pricePerKm;

    public VehicleDetails(String registrationNumber, Users vehicleOwner, String seatQuantity, Double pricePerKm) {
        this.registrationNumber = registrationNumber;
        this.vehicleOwner = vehicleOwner;
        this.seatQuantity = seatQuantity;
        this.pricePerKm = pricePerKm;
    }
}
