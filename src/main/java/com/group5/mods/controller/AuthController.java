package com.group5.mods.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.repository.UserRepository;
import com.group5.mods.service.UserService;

@Controller
public class AuthController {

    @Autowired
    UserService userService;
    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    // @PostMapping("/login")
    // public

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("user", new User());
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        UserDetails existingUserByUsername = userService.loadUserByUsername(user.getUsername());
        UserDetails existingUserByEmail = userService.loadUserByEmail(user.getEmail());
        // Check if the user with the same username or email already exists in the
        // database
        if (existingUserByUsername != null || existingUserByEmail != null) {
            model.addAttribute("errorMessage", "Username or email already exists, please enter new username or email.");
            return "register";
        }
        // If there is no user with the given username or email in the database then we
        // add the new user
        userRepository.save(user);
        model.addAttribute("successMessage", "User has been registered!");
        model.addAttribute("user", new User());
        return "register";

    }
}
