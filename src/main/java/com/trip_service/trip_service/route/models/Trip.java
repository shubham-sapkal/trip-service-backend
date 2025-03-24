package com.trip_service.trip_service.route.models;


import com.trip_service.trip_service.users.models.Users;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "trip_details")
@Getter
@Setter
public class Trip {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID tripId;

    @ManyToOne
    @JoinColumn( name = "created_by", referencedColumnName = "userName", nullable = false)
    private Users createdBy;

    @ManyToOne
    @JoinColumn(name = "vehicle_id", nullable = false)
    private VehicleDetails vehicleDetails;

    @ManyToOne
    @JoinColumn(name = "route-id", nullable = false)
    private Route tripRoute;

    @ManyToOne
    @JoinColumn(name = "driver", referencedColumnName = "userName",nullable = false)
    private Users driver;

    @Column(nullable = false)
    private LocalDateTime tripStartDate;

    @Column(nullable = false)
    private LocalDateTime tripEndDate;

    private boolean isReturnTrip;

    private boolean isTripCompleted = false;

}
