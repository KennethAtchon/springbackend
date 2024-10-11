package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfig {



    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())  // Disable CSRF for simplicity (not recommended for production)

            .authorizeHttpRequests(auth -> auth
                .requestMatchers("/signup", "/signin", "/css/**", "/js/**", "/images/**").permitAll()  // Public routes
                .requestMatchers("/", "/products", "/orders", "/customers").authenticated()  // Protected routes
            )

            .formLogin(form -> form
                .loginPage("/signin")  // Custom login page (Thymeleaf template)
                .defaultSuccessUrl("/", true)  // Redirect to home page upon successful login
                .permitAll()  // Allow everyone to access login page
            )

            .logout(logout -> logout
                .logoutUrl("/logout")  // Default logout URL
                .logoutSuccessUrl("/signin")  // Redirect to sign-in page after successful logout
                .permitAll()  // Allow logout URL to be accessed by everyone
            );

        return http.build();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

}
