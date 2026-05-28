package com.travel.util;

import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.List;

@Component
public class JwtFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain chain
    ) throws IOException, ServletException {

        try {
            String header = request.getHeader("Authorization");

            if (header == null || !header.startsWith("Bearer ")) {
                chain.doFilter(request, response);
                return;
            }

            String token = header.substring(7);

            // Validate token first
            if (!JwtUtil.validateToken(token)) {
                chain.doFilter(request, response);
                return;
            }

            String email = JwtUtil.extractEmail(token);
            String role = JwtUtil.extractRole(token);

            if (email != null && role != null) {

                System.out.println("JWT DEBUG: path=" + request.getRequestURI());
                System.out.println("JWT DEBUG: tokenRole(raw)=" + role);
                // Normalize role for Spring Security (expects ROLE_<ROLE>)
                String normalizedRole = role.trim().toUpperCase();

                // If JWT already contains "ROLE_ADMIN", avoid ROLE_ROLE_ADMIN
                String authority =
                        normalizedRole.startsWith("ROLE_")
                                ? normalizedRole
                                : "ROLE_" + normalizedRole;

                // Set auth only if not already set
                if (SecurityContextHolder.getContext().getAuthentication() == null) {
                    System.out.println("JWT DEBUG: tokenEmail=" + email);
                    System.out.println("JWT DEBUG: authority=" + authority);

                    UsernamePasswordAuthenticationToken auth =
                            new UsernamePasswordAuthenticationToken(
                                    email,
                                    null,
                                    List.of(new SimpleGrantedAuthority(authority))
                            );

                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }

            chain.doFilter(request, response);

        } catch (Exception e) {
            System.err.println("JWT Filter Error: " + e.getMessage());
            chain.doFilter(request, response);
        }
    }
}