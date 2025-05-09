package com.trip_service.trip_service.security.middlewares

import com.fasterxml.jackson.databind.ObjectMapper
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.AuthenticationException
import org.springframework.security.web.AuthenticationEntryPoint
import org.springframework.stereotype.Component

@Component
class AuthenticationMiddleware : AuthenticationEntryPoint {

    override fun commence(
        request: HttpServletRequest?,
        response: HttpServletResponse?,
        authException: AuthenticationException?
    ) {

        response?.status = HttpServletResponse.SC_UNAUTHORIZED;
        response?.contentType = "application/json";

        // Create JSON Response Body
        val errorResponse = mutableMapOf<String, Any>();

        errorResponse["status"] =  401;
        errorResponse["error"] =  "Unauthorized";
        errorResponse["message"] = authException?.message ?: "";
        errorResponse["path"] =  request?.requestURI ?: "";

        // Convert Map to JSON
        val objectMapper: ObjectMapper = ObjectMapper();
        val jsonResponse: String = objectMapper.writeValueAsString(errorResponse);

        // Send Json Response
        response?.writer?.write(jsonResponse);

    }

}