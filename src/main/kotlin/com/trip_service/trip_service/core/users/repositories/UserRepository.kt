package com.trip_service.trip_service.core.users.repositories

import com.trip_service.trip_service.core.users.models.Users
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<Users, String>  {
}