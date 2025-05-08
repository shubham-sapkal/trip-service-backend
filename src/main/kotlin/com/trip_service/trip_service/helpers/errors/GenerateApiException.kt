package com.trip_service.trip_service.helpers.errors

class GenerateApiException (
    val status: Number = 500,
    val errorMessage: String = "Internal Server Error"
) : RuntimeException(errorMessage)