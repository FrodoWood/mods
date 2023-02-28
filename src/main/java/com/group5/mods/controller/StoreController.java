package com.group5.mods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.group5.mods.repository.ProductRepository;

@Controller
public class StoreController {

    @Autowired
    ProductRepository productRepository;

    @GetMapping("/store")
    public String store(Model model) {
        model.addAttribute("products", productRepository.findAll());
        return "store";
    }

    @GetMapping("/store/Coilovers")
    public String storeCoilovers(Model model) {
        model.addAttribute("products", productRepository.findByCategory("Coilovers").get());
        return "store";
    }

    @GetMapping("/store/Turbo")
    public String storeTurbo(Model model) {
        model.addAttribute("products", productRepository.findByCategory("Turbo").get());
        return "store";
    }

    @GetMapping("/store/Brakes")
    public String storeBrakes(Model model) {
        model.addAttribute("products", productRepository.findByCategory("Brakes").get());
        return "store";
    }

    @GetMapping("/store/Oils")
    public String storeOils(Model model) {
        model.addAttribute("products", productRepository.findByCategory("Oils").get());
        return "store";
    }

    @GetMapping("/store/Batteries")
    public String storeBatteries(Model model) {
        model.addAttribute("products", productRepository.findByCategory("Batteries").get());
        return "store";
    }
}
