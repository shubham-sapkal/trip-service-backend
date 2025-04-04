package com.trip_service.trip_service.security.config;

import com.trip_service.trip_service.security.filters.AuthenticationFilter;
import com.trip_service.trip_service.security.middlewares.AuthenticationMiddleware;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private AuthenticationMiddleware middleware;

    @Autowired
    private AuthenticationFilter filter;

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http.csrf( csrf -> csrf.disable() )
                .authorizeRequests()
                .requestMatchers("/test").authenticated()
                .requestMatchers("/users/login").permitAll()
                .requestMatchers("/users/create").permitAll()
                .anyRequest()
                .authenticated()
                .and()
                .exceptionHandling( ex -> ex.authenticationEntryPoint(middleware))
                .sessionManagement( session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)   );


        http.addFilterBefore(filter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }

}
