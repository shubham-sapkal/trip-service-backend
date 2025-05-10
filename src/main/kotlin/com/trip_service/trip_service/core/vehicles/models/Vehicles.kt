package com.trip_service.trip_service.core.vehicles.models

import com.trip_service.trip_service.core.users.models.Users
import jakarta.persistence.*
import org.springframework.boot.autoconfigure.web.WebProperties.Resources.Chain.Strategy
import java.time.LocalDateTime
import java.util.UUID

enum class FuelType {
    PETROL,
    DIESEL,
    EV,
    HYBRID
}


@Entity
@Table(name = "vehicles", schema = "public")
class Vehicles(

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    var vehicleId: UUID? = null,

    @Column(unique = true, nullable = false)
    var vehicleRegNo: String,

    @ManyToOne
    var vehicleOwner: Users,

    @Column(nullable = false)
    var totalSeats: Int,

    @Column(nullable = false)
    var pricePerKm: Double,

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    var fuelType: FuelType,

    @Column
    var isACAvailable: Boolean = false,

    @Column
    var createdAt: LocalDateTime = LocalDateTime.now()
)
