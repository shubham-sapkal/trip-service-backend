package com.trip_service.trip_service.security.filters

import com.fasterxml.jackson.databind.ObjectMapper
import com.trip_service.trip_service.core.users.models.Users
import com.trip_service.trip_service.core.users.services.UserService
import com.trip_service.trip_service.security.DTO.DTO
import com.trip_service.trip_service.security.jwt.JwtUtils
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.MalformedJwtException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.GrantedAuthority
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.security.core.userdetails.UserDetailsService
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter
import java.util.Optional

@Component
class AuthenticationFilter : OncePerRequestFilter() {


    private val logger: Logger = LoggerFactory.getLogger(OncePerRequestFilter::class.java);

    @Autowired
    private lateinit var jwtUtils: JwtUtils;

    @Autowired
    private lateinit var userServices: UserService;

    @Autowired
    private lateinit var springUserService: UserDetailsService;


    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {

        try {

            // Authorization
            if(request.getHeader("Authorization") == null) {
                filterChain.doFilter(request, response);
                return ;
            }

            val requestHeader: String = request.getHeader("Authorization");

            var username: String? = null;
            var token: String? = null;

            if(requestHeader.startsWith("Bearer")) {

                // Here means, Bearer Token is valid

                token = requestHeader.substring(7);

                try {
                    username = this.jwtUtils.getUsernameFromToken(token);
                }
                catch (e: IllegalArgumentException) {
                    logger.info("Illegal Argument while fetching the username !!")
                    e.printStackTrace()
                } catch (e: ExpiredJwtException) {
                    logger.info("Given jwt token is expired !!");
                    e.printStackTrace();
                } catch (e: MalformedJwtException) {
                    logger.info("Some changed has done in token !! Invalid Token");
                    e.printStackTrace();
                } catch (e: Exception) {
                    e.printStackTrace();
                }

            }
            else {
                logger.info("Invalid Header Value!!");
            }

            if( username != null && SecurityContextHolder.getContext().authentication == null ) {

                // Extract User Information
                val user: Users = this.userServices.getUserById(username);


                if(token != null) {
                    val validateToken: Boolean = this.jwtUtils.validateToken(token, DTO.GenerateToken(
                        user.username,
                        user.email,
                        user.roles
                    ));

                    var grantedAuthorities: List<GrantedAuthority> = ArrayList();
                    val userRole = user.roles;

                    if(userRole.isNotEmpty()) {
                        grantedAuthorities = user.roles.map { SimpleGrantedAuthority(it) }
                    }

                    if (validateToken) {
                        val authentication = UsernamePasswordAuthenticationToken(
                            user,
                            null,
                            grantedAuthorities
                        );

                        authentication.details = WebAuthenticationDetailsSource().buildDetails(request);
                        SecurityContextHolder.getContext().authentication = authentication;
                    }
                    else {
                        logger.info("Validation Failed!!")
                    }
                }

            }

            // Your auth logic, e.g., token extraction, verification
            filterChain.doFilter(request, response)

        } catch (ex: Exception) {
            // Log the exception or optionally rethrow
            response.status = HttpServletResponse.SC_INTERNAL_SERVER_ERROR
            response.contentType = "application/json"
            val errorResponse = mapOf(
                "status" to 500,
                "error" to "Internal Server Error",
                "message" to ex.message,
                "path" to request.requestURI
            )
            val json = ObjectMapper().writeValueAsString(errorResponse)
            response.writer.write(json)
        }

    }
}