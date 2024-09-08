package com.example.demo.service;

import com.example.demo.model.AdminUser;
import com.example.demo.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;

    // Get all admin users
    public List<AdminUser> getAllAdminUsers() {
        return adminUserRepository.findAll();
    }

    // Get admin user by ID
    public Optional<AdminUser> getAdminUserById(Long id) {
        return adminUserRepository.findById(id);
    }

    // Get admin user by username
    public Optional<AdminUser> getAdminUserByUsername(String username) {
        return adminUserRepository.findByUsername(username);
    }

    // Get admin user by email
    public Optional<AdminUser> getAdminUserByEmail(String email) {
        return adminUserRepository.findByEmail(email);
    }

    // Save or update an admin user
    public AdminUser saveAdminUser(AdminUser adminUser) {
        return adminUserRepository.save(adminUser);
    }

    // Delete admin user by ID
    public void deleteAdminUser(Long id) {
        adminUserRepository.deleteById(id);
    }
}
