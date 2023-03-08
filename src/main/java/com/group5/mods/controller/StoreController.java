package com.group5.mods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group5.mods.model.Product;
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

    @GetMapping("/store/search/{searchValue}")
    public String storeSearch(@PathVariable String searchValue, Model model) {
        List<Product> test = productRepository.findAll();
        List<Product> result = new ArrayList<Product>();
        for (Product product : test) {
            if (product.getName().toLowerCase().compareTo(searchValue.toLowerCase()) == 0) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            return "store";
        } else {
            model.addAttribute("products", result);
            return "store";
        }
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
