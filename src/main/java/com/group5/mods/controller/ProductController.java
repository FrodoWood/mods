package com.group5.mods.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import com.group5.mods.repository.ProductRepositary;

@Controller
public class ProductController {
    @Autowired
    ProductRepositary productRepasitory;

    @GetMapping("/product")
    public String product(Model model) {
        return "product";
    }
}
