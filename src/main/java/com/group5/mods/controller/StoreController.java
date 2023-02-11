package com.group5.mods.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StoreController {
    @GetMapping("/store")
    public String store(Model model) {
        return "store";
    }
}
