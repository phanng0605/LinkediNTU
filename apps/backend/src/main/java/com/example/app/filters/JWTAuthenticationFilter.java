package com.example.app.filters;

import com.example.app.exceptions.ModelException;
import com.example.app.exceptions.ServiceException;
import com.example.app.services.JWTSessionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import org.springframework.web.server.WebFilter;
import org.springframework.web.server.WebFilterChain;
import reactor.core.publisher.Mono;

@Component
public class JWTAuthenticationFilter implements WebFilter {

    private final JWTSessionService jwtSessionService;

    @Autowired
    public JWTAuthenticationFilter(JWTSessionService jwtSessionService) {
        this.jwtSessionService = jwtSessionService;
    }

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, WebFilterChain chain) {
        String path = exchange.getRequest().getPath().toString();
        // Apply only to "/api/*"
        if (path.matches("^/api/v[^/]+(/.*)?") && !path.matches("^/api/v[^/]+/auth(/.*)?")) {
            String authHeader = exchange.getRequest().getHeaders().getFirst(HttpHeaders.AUTHORIZATION);

            if (authHeader == null || !authHeader.startsWith("Bearer ")) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }

            String token = authHeader.substring(7); // Remove "Bearer " prefix

            try {
                return jwtSessionService.validateSession(token).flatMap(session -> {
                    System.out.println("Session: " + session);
                    if (session == null) {
                        exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                        return exchange.getResponse().setComplete();
                    }
                    exchange.getAttributes().put("session", session);
                    return chain.filter(exchange);
                }).onErrorResume(e -> {
                    exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                    return exchange.getResponse().setComplete();
                });
            } catch (ServiceException | ModelException e) {
                exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
                return exchange.getResponse().setComplete();
            }
        }

        System.out.println("Path: " + path);
        return chain.filter(exchange);
    }
}