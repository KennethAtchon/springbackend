package com.example.demo.controllers.admin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.example.demo.components.SignInForm;
import com.example.demo.components.SignUpForm;
import com.example.demo.model.AdminUser;
import com.example.demo.service.AdminUserService;


import java.util.Optional;

@Controller
public class CreateController {

    @Autowired
    private AdminUserService adminUserService;

    @Autowired
    private AuthenticationManager authenticationManager; 


    // Handle SignUp
    @PostMapping("/signup")
    public String createAdminUser(@ModelAttribute(name = "SignUpForm") SignUpForm signUpForm, Model model) {
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
    
        try {
            // Automatically log in the newly created user
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);
    
            // Redirect to the home page after successful signup and login
            return "redirect:/";
        } catch (AuthenticationException e) {
            // If authentication fails, return to the signup page with an error message
            model.addAttribute("error", "Signup was successful, but auto-login failed.");
            return "auth/signup";
        }

    }
    

    // Handle SignIn    
    @PostMapping("/signin")
    public String loginAdminUser(@ModelAttribute(name = "SignInForm") SignInForm signInForm, Model model) {
        String username = signInForm.getUsername();
        String password = signInForm.getPassword();


        
        try {
            // Attempt to authenticate the user
            UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(username, password);
            Authentication authentication = authenticationManager.authenticate(authToken);
            SecurityContextHolder.getContext().setAuthentication(authentication);


            return "redirect:/";  // Redirect on successful login
        } catch (AuthenticationException e) {
            // Authentication failed
            model.addAttribute("error", "Invalid username or password");
            return "auth/signin";  // Return to sign-in page with error
        }
    }
    
}
