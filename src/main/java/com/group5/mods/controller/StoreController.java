package com.group5.mods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.annotation.MergedAnnotations.Search;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

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

    @GetMapping("/store/makeModel")
    public String storeMakeModel(@RequestParam("make") String make, @RequestParam("model") String carmodel,
            Model model) {
        List<Product> temp = productRepository.findByMakeAndModel(make, carmodel);
        // List<Product> temp = productRepository.findAll();
        model.addAttribute("products", temp);
        return "store";
    }

    @GetMapping("/store/{price}")
    public String storePrice(@PathVariable String price, Model model) {
        List<Product> rawData = productRepository.findAll();
        ArrayList<Product> result = new ArrayList<>();
        for (Product product : rawData) {
            if (price.contentEquals("low")) {
                if (product.getPrice().intValue() < 100) {
                    result.add(product);
                }
            }
            if (price.contentEquals("mid")) {
                if (product.getPrice().intValue() < 1000) {
                    result.add(product);
                }
            }
            if (price.contentEquals("high")) {
                if (product.getPrice().intValue() > 1000) {
                    result.add(product);
                }
            }
        }
        model.addAttribute("products", result);
        return "store";
    }

    @GetMapping("/store/search")
    public String storeSearch(@RequestParam("searchValue") String search, Model model) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> result = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(search.toLowerCase())) {
                result.add(product);
            }
        }
        if (result.isEmpty()) {
            String error = "No items for that search";
            model.addAttribute("error", error);
            return "store";
        } else {
            String size = Integer.toString(result.size());
            model.addAttribute("products", result);
            model.addAttribute("size", size);
            return "store";
        }
    }

    @GetMapping("/store/category/{category}")
    public String storeCategoryFilter(@PathVariable String category, Model model) {
        Optional<List<Product>> categoryProducts = productRepository.findAllByCategory(category);
        // List<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", categoryProducts.get());
        return "store";
    }
}
