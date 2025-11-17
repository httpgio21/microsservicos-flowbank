package com.ms.conta.security;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
protected void doFilterInternal(HttpServletRequest request,
                                HttpServletResponse response,
                                FilterChain filterChain) throws ServletException, IOException {

    String path = request.getServletPath();
    String method = request.getMethod();

    // Permite criação de conta sem JWT
    if (path.equals("/contas") && method.equals("POST")) {
        filterChain.doFilter(request, response);
        return;
    }

    // Para os outros endpoints, exige JWT
    String authHeader = request.getHeader("Authorization");
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
        response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
        return;
    }

    String token = authHeader.substring(7);
    if (!JwtUtil.validateToken(token)) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return;
    }

    filterChain.doFilter(request, response);
}
}
