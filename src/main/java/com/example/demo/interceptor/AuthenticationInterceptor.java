package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.example.demo.util.JwtTokenUtil;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        String path = request.getRequestURI();
        System.out.println("Path: " + path);
        // Check for the Authorization header (Bearer token)
        String authHeader = request.getHeader("Authorization");
        System.out.println("Authorization Header: " + authHeader);
        
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            // Print error to standard output
            System.out.println("Missing or invalid Authorization header.");
            
            // Redirect to /signin
            response.sendRedirect("/signin");
            return false;
        }

        // Extract token
        String token = authHeader.substring(7);  // Remove "Bearer " prefix

        try {
            // Extract the username from the token
            String username = jwtTokenUtil.extractUsername(token);

            // Validate token
            if (!jwtTokenUtil.validateToken(token, username)) {
                // Print error to standard output
                System.out.println("Invalid or expired token.");
                
                // Redirect to /signin
                response.sendRedirect("/signin");
                return false;
            }
            System.out.println("Valid token.");
            // If the token is valid, proceed with the request
            return true;

        } catch (Exception e) {
            // Handle JWT parsing or validation exceptions
            System.out.println("Invalid token: " + e.getMessage());
            
            // Redirect to /signin
            response.sendRedirect("/signin");
            return false;
        }
    }
}
