package com.trip_service.trip_service.core.routes.repositories

import com.trip_service.trip_service.core.routes.models.Routes
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface RouteRepository : JpaRepository<Routes, UUID> {
}