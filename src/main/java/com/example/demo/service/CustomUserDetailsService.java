package com.example.demo.service;

import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService; // Your user service
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private AdminUserService adminUserService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Implement the logic to retrieve the user by username or email
        AdminUser adminUser = adminUserService.getAdminUserByUsername(username)
            .orElseThrow(() -> new UsernameNotFoundException("User not found"));

        return new CustomUserDetails(adminUser); // Return your UserDetails implementation
    }
}
