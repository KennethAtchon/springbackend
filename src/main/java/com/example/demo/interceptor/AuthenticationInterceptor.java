package com.example.demo.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

@Component
public class AuthenticationInterceptor implements HandlerInterceptor {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        String path = request.getRequestURI();
        System.out.println(path);

        // Allow static resources
        if (path.startsWith("/css") || path.startsWith("/images") || path.startsWith("/js")) {
            System.out.println("Static resource request, proceeding without authentication.");
            return true;  // Allow the request to proceed for static resources
        }

        // Check for authorization token
        String token = request.getHeader("Authorization");
        if (token == null || !isValidToken(token)) {
            response.sendRedirect("/signin");
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            return false;
        }

        return true;
    }

    private boolean isValidToken(String token) {
        // Logic to validate the token
        return true; // Simplified for this example
    }
}
