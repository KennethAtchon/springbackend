package com.example.demo.service;

import com.example.demo.config.PasswordHasher;
import com.example.demo.model.AdminUser;
import com.example.demo.model.Project;
import com.example.demo.repository.AdminUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AdminUserService {

    @Autowired
    private AdminUserRepository adminUserRepository;
    
    @Autowired
    private ProjectService projectService;
    
    @Autowired
    private PasswordHasher passwordHasher;

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
        // Hash the password before saving
        String hashedPassword = passwordHasher.hashPassword(adminUser.getPasswordHash());
        adminUser.setPasswordHash(hashedPassword);

        // Save the admin user
        AdminUser savedAdminUser = adminUserRepository.save(adminUser);

        // Create a new project for the admin user
        Project project = new Project();
        project.setAdminUser(savedAdminUser);
        project.setName(savedAdminUser.getUsername() + "'s Project");  // Example project name
        project.setDescription("Default project for " + savedAdminUser.getUsername());
        projectService.saveProject(project);

        return savedAdminUser;
    }

    public AdminUser saveAdminUserWithProject(AdminUser adminUser, String projectName) {
        // Hash the password before saving
        String hashedPassword = passwordHasher.hashPassword(adminUser.getPasswordHash());
        adminUser.setPasswordHash(hashedPassword);

        // Save the admin user
        AdminUser savedAdminUser = adminUserRepository.save(adminUser); 

        // Validate the project name
        if (projectName == null || projectName.trim().isEmpty()) {
            throw new IllegalArgumentException("Project name cannot be null or empty");
        }

        // Create a new project for the admin user with the provided project name
        Project project = new Project();
        project.setAdminUser(savedAdminUser);
        project.setName(projectName);  // Use the provided project name
        project.setDescription("Default project description for " + savedAdminUser.getUsername());
        projectService.saveProject(project);

        return savedAdminUser;
    }

    // Delete admin user by ID
    public void deleteAdminUser(Long id) {
        adminUserRepository.deleteById(id);
    }
}
