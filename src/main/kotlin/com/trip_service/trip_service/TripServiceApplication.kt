package com.trip_service.trip_service

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class TripServiceApplication

fun main(args: Array<String>) {
	runApplication<TripServiceApplication>(*args)
}
