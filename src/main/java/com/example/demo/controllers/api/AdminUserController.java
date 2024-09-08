package com.example.demo.controllers.api;

import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/admin-users")
public class AdminUserController {

    @Autowired
    private AdminUserService adminUserService;

    // Get all admin users
    @GetMapping
    public List<AdminUser> getAllAdminUsers() {
        return adminUserService.getAllAdminUsers();
    }

    // Get admin user by ID
    @GetMapping("/{id}")
    public ResponseEntity<AdminUser> getAdminUserById(@PathVariable Long id) {
        Optional<AdminUser> adminUser = adminUserService.getAdminUserById(id);
        return adminUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Create a new admin user
    @PostMapping
    public AdminUser createAdminUser(@RequestBody AdminUser adminUser) {
        return adminUserService.saveAdminUser(adminUser);
    }

    // Update an existing admin user
    @PutMapping("/{id}")
    public ResponseEntity<AdminUser> updateAdminUser(@PathVariable Long id, @RequestBody AdminUser adminUser) {
        if (adminUserService.getAdminUserById(id).isPresent()) {
            adminUser.setId(id);
            return ResponseEntity.ok(adminUserService.saveAdminUser(adminUser));
        }
        return ResponseEntity.notFound().build();
    }

    // Delete an admin user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAdminUser(@PathVariable Long id) {
        if (adminUserService.getAdminUserById(id).isPresent()) {
            adminUserService.deleteAdminUser(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}
