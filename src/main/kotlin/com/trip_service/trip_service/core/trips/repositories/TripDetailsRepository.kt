package com.trip_service.trip_service.core.trips.repositories

import com.trip_service.trip_service.core.trips.models.TripDetails
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface TripDetailsRepository : JpaRepository<TripDetails, UUID>{
}