/*
package com.gms.backend.security;

import com.gms.backend.customer.Customer;
import com.gms.backend.customer.CustomerRepository;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Component
public class JwtFilter extends OncePerRequestFilter {

    private final JwtUtil jwtUtil;
    private final CustomerRepository customerRepo;

    public JwtFilter(JwtUtil jwtUtil, CustomerRepository customerRepo) {
        this.jwtUtil = jwtUtil;
        this.customerRepo = customerRepo;
    }
    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain filterChain)
            throws ServletException, IOException {

        try {
            String authHeader = request.getHeader("Authorization");

            if (authHeader != null && authHeader.startsWith("Bearer ")) {

                String token = authHeader.substring(7);

                // ✅ STEP 1: Basic format check
                if (token == null || !token.contains(".")) {
                    filterChain.doFilter(request, response);
                    return;
                }

                try {
                    // ✅ STEP 2: Extract safely
                    String email = jwtUtil.extractUsername(token);

                    Customer user = customerRepo.findByEmail(email).orElse(null);

                    CurrentUser.set(user);

                } catch (Exception e) {
                    // ✅ STEP 3: Ignore invalid token
                    System.out.println("Invalid JWT token: " + e.getMessage());
                }
            }

            filterChain.doFilter(request, response);

        } finally {
            CurrentUser.clear();
        }
    }
}*/
