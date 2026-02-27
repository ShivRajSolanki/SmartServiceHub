package com.smarhub.api_gateway.Security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

@Component
public class JwtGatewayFilter implements GlobalFilter {

    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {

        String path = exchange.getRequest().getURI().getPath();
        System.out.println("Incoming Path: " + path);


        // Allow login & register without token
        if (path.startsWith("/users/login") || path.startsWith("/users/register")) {
            System.out.println("Bypass condition hit");

            return chain.filter(exchange);
        }


        String authHeader = exchange.getRequest().getHeaders().getFirst("Authorization");

        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        System.out.println("Authorization Header: " + authHeader);

        String token = authHeader.substring(7);

        if (!jwtUtil.validateToken(token)) {
            exchange.getResponse().setStatusCode(HttpStatus.UNAUTHORIZED);
            return exchange.getResponse().setComplete();
        }
        String role = jwtUtil.extractRole(token);
        String email = jwtUtil.extractEmail(token);

// Inject email in header
        exchange = exchange.mutate()
                .request(exchange.getRequest()
                        .mutate()
                        .header("X-User-Email", email)
                        .build())
                .build();

        // 🔥 RBAC CHECK HERE
        if (path.contains("/services") &&
                exchange.getRequest().getMethod().name().equals("POST")) {

            if (!"ADMIN".equals(role)) {
                exchange.getResponse().setStatusCode(HttpStatus.FORBIDDEN);
                return exchange.getResponse().setComplete();
            }
        }

        return chain.filter(exchange);
    }

}
