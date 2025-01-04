package org.szarry.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.szarry.entities.AppUser;
import org.szarry.entities.Role;
import org.szarry.services.UserService;
import org.szarry.repositories.RoleRepository;

import java.util.HashSet;
import java.util.Set;

@Controller
public class RegisterController {

    @Autowired
    private UserService userService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        // Dodaj pusty obiekt AppUser do modelu
        model.addAttribute("user", new AppUser());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@RequestParam String username, @RequestParam String password, @RequestParam String email, Model model) {
        // Sprawdzanie unikalności użytkownika
        if (userService.existsByUsername(username)) {
            model.addAttribute("error", "Username already exists");
            return "register";
        }

        // Szyfrowanie hasła i rejestracja użytkownika
        String encodedPassword = passwordEncoder.encode(password);
        try {
            userService.registerUser(username, encodedPassword, email);
        } catch (RuntimeException e) {
            model.addAttribute("error", e.getMessage());
            return "register";
        }

        return "redirect:/login";
    }
}