package com.group5.mods.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller

public class HomeController extends BaseController {
    @GetMapping("/")
    public String homepage(Model model) {
        return "index";
    }

    @GetMapping("/user")
    @PreAuthorize("hasRole('USER')")
    public String user() {
        return "index";
    }

    @GetMapping("/contact")
    public String contact(){
        return "contact";
    }

    @PostMapping("/contact/send")
    public String contact(@RequestParam("email") String email, Model model){
        model.addAttribute("success", "Message sent!");
        return "contact";
    }
    
    @GetMapping("/about")
    public String about(){
        return "about";
    }
}
