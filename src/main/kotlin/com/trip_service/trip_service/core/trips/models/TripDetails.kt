package com.trip_service.trip_service.core.trips.models

import com.trip_service.trip_service.core.routes.models.Routes
import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.vehicles.models.Vehicles
import jakarta.persistence.*
import java.time.LocalDateTime
import java.util.UUID

enum class TripStatus { PLANNED, ACTIVE, COMPLETED, CANCELLED }

@Entity
@Table(name = "trip_details")
class TripDetails(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    val tripId: UUID? = null,

    @Column
    val tripName: String,

    @Column
    val tripDescription: String,

    @Column
    val perPersonCost: Double,

    @ManyToOne
    val createdBy: Users,

    @ManyToOne
    val driver:  Users,

    @ManyToOne
    val tripVehicle: Vehicles,

    @ManyToOne
    val tripRoutes: Routes,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    val tripStatus: TripStatus,

    @Column
    val tripStartDate: LocalDateTime,

    @Column
    val tripEndDate: LocalDateTime,

    @Column
    val isReturnTrip: Boolean? = false,

    @Column
    val isAcAvailable: Boolean? = false,

    @Column
    val createdAt:  LocalDateTime? = LocalDateTime.now(),

    @Column
    val updatedAt: LocalDateTime? = null

) {
}