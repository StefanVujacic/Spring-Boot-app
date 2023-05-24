package com.example.demo.configuration;

import com.example.demo.util.JWT_util;
import io.jsonwebtoken.ExpiredJwtException;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;


@Component
public class JWT_request_filter extends OncePerRequestFilter {

    @Autowired
    JWT_util jwt_util;

    public static String current_user = "";

    @Autowired
    CustomUserDetailsService jwt_service;

    public JWT_request_filter() {
    }

    public JWT_request_filter(JWT_util jwt_util, CustomUserDetailsService jwt_service) {
        this.jwt_util = jwt_util;
        this.jwt_service = jwt_service;
    }

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        final String header = request.getHeader("authorisation");
        String jwtToken = null;
        String username = null;
        if (header != null && header.startsWith("Bearer ")) {
            jwtToken = header.substring(7);
            try {
                username = jwt_util.getUsernameFromToken(jwtToken);
                current_user = username;
            } catch (IllegalArgumentException illegal) {
                System.out.println("Unable to get JWT token");
            } catch (ExpiredJwtException jwtException) {
                System.out.println("JWT token is expired");
            }

        } else {
            System.out.println("JWT token does not start with bearer");
        }

        if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            UserDetails userDetails = jwt_service.loadUserByUsername(username);

            if (jwt_util.validate_token(jwtToken, userDetails)) {
                UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());

                usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));

                SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            }
        }
        filterChain.doFilter(request, response);

    }
}
