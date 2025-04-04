package com.trip_service.trip_service.security.filters;

import com.trip_service.trip_service.security.helper.DTOEntity;
import com.trip_service.trip_service.security.helper.JwtUtils;
import com.trip_service.trip_service.users.models.Users;
import com.trip_service.trip_service.users.services.UserServices;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class AuthenticationFilter extends OncePerRequestFilter {

    private Logger logger = LoggerFactory.getLogger(OncePerRequestFilter.class);

    @Autowired
    private JwtUtils jwtUtils;

    @Autowired
    private UserServices userService;

    @Autowired
    UserDetailsService springUserService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {

        // Authorization
        String requestHeader = request.getHeader("Authorization");

        String username = null;
        String token = null;

        if(requestHeader != null && requestHeader.startsWith("Bearer")) {

            // Bearer token is valid

            token = requestHeader.substring(7);

            try {
                username = this.jwtUtils.getUsernameFromToken(token);
            }
            catch (IllegalArgumentException e) {
                logger.info("Illegal Argument while fetching the username !!");
                e.printStackTrace();
            } catch (ExpiredJwtException e) {
                logger.info("Given jwt token is expired !!");
                e.printStackTrace();
            } catch (MalformedJwtException e) {
                logger.info("Some changed has done in token !! Invalid Token");
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }

        }
        else {
            logger.info("Invalid Header Value !!");
        }

        if(username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

            Optional<Users> optionalUserDetails = this.userService.getUserById(username);

            if (optionalUserDetails.isEmpty() ) {
                logger.info("User Not Found!!");
            }
            else {

                Users userDetails = optionalUserDetails.get();

                Boolean validateToken = this.jwtUtils.validateToken(token, new DTOEntity.TokenBodyDTO(
                        userDetails.getUserName(),
                        userDetails.getEmail(),
                        userDetails.getRole()
                ));


                List<GrantedAuthority> grantedAuthorities = new ArrayList<>();

                List<String> userRole = userDetails.getRole();

                if( userRole != null && !userRole.isEmpty()) {
                    grantedAuthorities = userDetails.getRole().stream()
                            .map(SimpleGrantedAuthority::new)
                            .collect(Collectors.toList());
                }

                if ( validateToken ) {

                    UsernamePasswordAuthenticationToken authentication = new UsernamePasswordAuthenticationToken(
                            userDetails,
                            null,
                            grantedAuthorities
                    );
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
                else {
                    logger.info("Validation Failed!!");
                }

            }

        }

        filterChain.doFilter(request, response);

    }
}
