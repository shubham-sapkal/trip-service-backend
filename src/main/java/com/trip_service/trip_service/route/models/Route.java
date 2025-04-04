package com.trip_service.trip_service.route.models;


import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "route")
@Getter
@Setter
public class Route {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID routeId;

    @Column(nullable = false)
    private String userId;

    @Column(nullable = false, unique = true)
    private String routeName;

    @Column(nullable = false)
    private String routeOrigin;

    @Column(nullable = false)
    private String routeDestination;

    @ElementCollection
    private List<StopLocations> routeStops = new ArrayList<>();

    // Method to add Stops
    public void addStops(List<StopLocations> stops ) {
        routeStops.addAll(stops);
    }

    // Method to delete routes
    public void removeStops(List<StopLocations> stops ) {
        routeStops.removeAll(stops);
    }

}
