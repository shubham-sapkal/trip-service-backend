package com.trip_service.trip_service.helpers.DTO

import lombok.Getter
import lombok.Setter

@Getter
@Setter
class GenerateResponse<T>(
    val status: Number = 200,
    val message: String = "Default Message",
    val errorMessage: String? = null,
    val result: T? = null
)