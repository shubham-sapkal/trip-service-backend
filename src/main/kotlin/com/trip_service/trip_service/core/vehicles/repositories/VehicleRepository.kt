package com.trip_service.trip_service.core.vehicles.repositories

import com.trip_service.trip_service.core.vehicles.models.Vehicles
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface VehicleRepository : JpaRepository<Vehicles, UUID> {

}