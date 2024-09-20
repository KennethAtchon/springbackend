package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
                .antMatchers("/", "/signup", "/signin", "/css/**", "/js/**").permitAll()  // Open routes
                .antMatchers("/products", "/orders", "/customers").authenticated()  // Protected routes
            .and()
                .formLogin()
                .loginPage("/signin")  // Custom login page
                .defaultSuccessUrl("/", true)  // Redirect after successful login
                .permitAll()
            .and()
                .logout()
                .logoutSuccessUrl("/signin")  // Redirect after successful logout
                .permitAll()
            .and()
                .csrf().disable();  // Disable CSRF for simplicity (can enable it later)
        
        return http.build();
    }
}
