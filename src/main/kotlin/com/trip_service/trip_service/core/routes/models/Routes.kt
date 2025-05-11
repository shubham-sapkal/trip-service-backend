package com.trip_service.trip_service.core.routes.models

import com.trip_service.trip_service.core.users.models.Users
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

@Entity
@Table(name = "routes", schema = "public")
class Routes (

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val routeId: UUID? = null,

    @Column(nullable = false, unique = true)
    val routeName: String,

    @Column(nullable = false)
    val routeOrigin: RouteStops,

    @Column(nullable = false)
    val routeDestination: RouteStops,

    @Column(nullable = false)
    val totalDistance: Double,

    @ElementCollection
    val routeStops: MutableList<RouteStops>,

    @ManyToOne
    val createdBy: Users,

    @Column
    val createdAt: LocalDateTime = LocalDateTime.now()

) {

    // Method to Add All Stops
    fun addStops(stops: List<RouteStops>) {
        routeStops.addAll(stops);
    }

    // Method to Remove All Stops
    fun removeStops(stops: List<RouteStops>) {
        routeStops.removeAll(stops);
    }

}