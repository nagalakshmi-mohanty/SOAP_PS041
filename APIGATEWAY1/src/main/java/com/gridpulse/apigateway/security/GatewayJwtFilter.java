package com.gridpulse.apigateway.security;

import org.springframework.stereotype.Component;

import org.springframework.web.servlet.function.ServerRequest;
import org.springframework.web.servlet.function.ServerResponse;

import java.util.function.Function;

@Component
public class GatewayJwtFilter {

    private final JwtAuthenticationFilter jwtAuthenticationFilter;

    public GatewayJwtFilter(JwtAuthenticationFilter jwtAuthenticationFilter) {
        this.jwtAuthenticationFilter = jwtAuthenticationFilter;
    }

    public Function<ServerRequest, ServerResponse> filter(
            Function<ServerRequest, ServerResponse> next) {

        return request -> {

            String path = request.path();

            // Auth endpoints do not require JWT
            if (path.startsWith("/auth/")) {
                return next.apply(request);
            }

            // Get Authorization header
            String authHeader = request.headers()
                    .firstHeader("Authorization");

            // No token
            if (authHeader == null ||
                    !authHeader.startsWith("Bearer ")) {

                return ServerResponse.status(401)
                        .body("Missing or invalid Authorization token");
            }

            // Extract token
            String token = authHeader.substring(7);

            // Validate token
            if (!jwtAuthenticationFilter.validateToken(token)) {

                return ServerResponse.status(401)
                        .body("Invalid or expired JWT token");
            }

            // Token valid
            return next.apply(request);
        };
    }
}