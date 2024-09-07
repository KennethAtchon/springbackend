package com.example.demo.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    @GetMapping("/")
    public String home() {
        return "home"; // This returns the view for the home page (home.html or home.jsp)
    }

    @GetMapping("/signin")
    public String signIn() {
        return "auth/signin"; // Looks for signin.html in templates/auth/
    }

    @GetMapping("/signup")
    public String signUp() {
        return "auth/signup"; // Looks for signup.html in templates/auth/
    }

    @GetMapping("/products")
    public String products() {
        return "products"; // products.html
    }

    @GetMapping("/orders")
    public String orders() {
        return "orders"; // orders.html
    }

    @GetMapping("/customers")
    public String customers() {
        return "customers"; // customers.html
    }

}
