package com.group5.mods.controller;

import org.hibernate.metamodel.mapping.internal.IdClassEmbeddable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group5.mods.repository.ProductRepository;

@Controller
public class ProductController {
    @Autowired
    ProductRepository productRepository;

    @GetMapping("/prduct/{id}")
    public String product(@PathVariable("id") long id, Model model) {
        model.addAttribute("product", productRepository.findById(id));
        return "product";
    }
}
