package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.components.SignInForm;
import com.example.demo.components.SignUpForm;
import com.example.demo.config.PasswordHasher;
import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService;


import java.util.Optional;

@Controller
public class CreateController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private PasswordHasher passwordHasher;


    // Handle SignUp
    @PostMapping("/signup")
    public String createAdminUser(@ModelAttribute(name="SignUpForm") SignUpForm signUpForm, Model model) {
        String username = signUpForm.getUsername();
        String password = signUpForm.getPassword();
        String email = signUpForm.getEmail();
        String projectName = signUpForm.getProjectName();

        AdminUser adminUser = new AdminUser();
        adminUser.setUsername(username);
        adminUser.setEmail(email);
        adminUser.setPasswordHash(password);  // Plain text password for now

        // Save the AdminUser and associate the project
        adminUserService.saveAdminUserWithProject(adminUser, projectName);



        // Redirect to home page after successful signup
        return "home";
    }

    // Handle SignIn    
    @PostMapping("/signin")
    public String loginAdminUser(@ModelAttribute(name="SignInForm") SignInForm signInForm, Model model) {
        String usernameOrEmail = signInForm.getUsername(); // Assuming the form field accepts both username and email
        String password = signInForm.getPassword();
    
        // Attempt to retrieve user by username
        Optional<AdminUser> adminUser = adminUserService.getAdminUserByUsername(usernameOrEmail);
    
        // If no user found by username, try retrieving by email
        if (!adminUser.isPresent()) {
            adminUser = adminUserService.getAdminUserByEmail(usernameOrEmail);
        }
    
        if (adminUser.isPresent()) {
            // Retrieve the hashed password from the database
            String hashedPassword = adminUser.get().getPasswordHash();
    
            // Use the PasswordHasher to verify the password
            if (passwordHasher.verifyPassword(password, hashedPassword)) {
                // Successful login, redirect to home page
                return "home";
            }
        }
    
        // If authentication fails, add an error message to the model
        model.addAttribute("error", "Invalid username or password");
        return "auth/signin";  // Return to sign-in page with an error message
    }
    
}
