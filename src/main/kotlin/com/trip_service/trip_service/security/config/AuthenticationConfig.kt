package com.trip_service.trip_service.security.config

import com.trip_service.trip_service.security.filters.AuthenticationFilter
import com.trip_service.trip_service.security.middlewares.AuthenticationMiddleware
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.config.http.SessionCreationPolicy
import org.springframework.security.web.SecurityFilterChain
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter

@Configuration
@EnableMethodSecurity
class AuthenticationConfig {

    @Autowired
    private lateinit var authMiddleware: AuthenticationMiddleware;

    @Autowired
    private lateinit var authFilter: AuthenticationFilter;

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {

        http.csrf { it.disable() }
            .authorizeRequests()
            .requestMatchers("/test").authenticated()
            .requestMatchers("/users/login").permitAll()
            .requestMatchers("/users/create").permitAll()
            .anyRequest().authenticated()
            .and()
            .exceptionHandling { it.authenticationEntryPoint(authMiddleware) }
            .sessionManagement { it.sessionCreationPolicy(SessionCreationPolicy.STATELESS) }

        http.addFilterBefore(authFilter, UsernamePasswordAuthenticationFilter::class.java)

        return http.build()

    }


}