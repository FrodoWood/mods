package com.group5.mods.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController

public class HomeController {
    @GetMapping("/")
    public String homepage() {
        return "Welcome to the homepage!";
    }

    @GetMapping("user")
    public String user() {
        return "Hello user";
    }

    @GetMapping("admin")
    public String admin() {
        return "Hello admin";
    }

}
