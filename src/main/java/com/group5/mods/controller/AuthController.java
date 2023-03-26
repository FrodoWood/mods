package com.group5.mods.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.group5.mods.DTO.UserDTO;
import com.group5.mods.model.User;
import com.group5.mods.repository.UserRepository;
import com.group5.mods.service.UserService;

import jakarta.validation.Valid;

@Controller
public class AuthController {

    @Autowired
    UserService userService;

    @Autowired
    UserRepository userRepository;

    @GetMapping("/login")
    public String login(Model model) {
        return "login";
    }

    @GetMapping("/register")
    public String showRegistrationForm(Model model) {
        model.addAttribute("userDTO", new UserDTO());
        return "register";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid UserDTO userDTO, BindingResult bindingResult, Model model) {
        
        if (bindingResult.hasErrors()) {
            model.addAttribute("failedMessage", "User registration failed!");
            model.addAttribute("bindingResult", bindingResult);
            return "/register";
        }

        Optional<User> existingUsername = userRepository.findByUsername(userDTO.getUsername());
        Optional<User> existingEmail = userRepository.findByEmail(userDTO.getEmail());

        if(existingUsername.isPresent()){
            model.addAttribute("usernameTakenError", "Username already taken");
            return "/register";
        }
        if(existingEmail.isPresent()){
            model.addAttribute("emailTakenError", "Email already in use");
            return "/register";
        }

        userService.saveUser(userDTO);
        model.addAttribute("successMessage", "User registered successfully!");
        return "/login";

    }
}
