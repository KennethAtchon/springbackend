package com.example.demo.controllers.api;

import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
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
    @PostMapping("/create")
    public String createAdminUser(
            @RequestParam("username") String username,
            @RequestParam("email") String email,
            @RequestParam("password") String password,
            @RequestParam("project-name") String projectName) {

        // Create the AdminUser object
        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setEmail(email);
        adminUser.setPasswordHash(password);  // You should encode the password

        // Save the AdminUser and create a Project with the provided project name
        adminUserService.saveAdminUserWithProject(adminUser, projectName);

        // Return the view for the home page
        return "Successfully created admin user and project for " + username + "!"; // This will render home.html
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
