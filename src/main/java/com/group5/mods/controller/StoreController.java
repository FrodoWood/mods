package com.group5.mods.controller;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import com.group5.mods.model.Product;
import com.group5.mods.repository.ProductRepository;

@Controller
public class StoreController extends BaseController {

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
        model.addAttribute("make", make);
        model.addAttribute("model", carmodel);
        model.addAttribute("filter", "filter");
        return "store";
    }

    @GetMapping("/store/price")
    public String storePrice(Model model, @RequestParam(required = false, defaultValue = "99999") BigDecimal maxPrice,
                                            @RequestParam(required = false, defaultValue = "low") String sort) {
        List<Product> allProducts = productRepository.findAll();
        
        // Filter products list by max price
        allProducts = allProducts.stream().filter(product -> product.getPrice().compareTo(maxPrice) <= 0)
                                            .collect(Collectors.toList());
        // Sort products
        if(sort.equals("low")){
            allProducts.sort(Comparator.comparing(Product::getPrice));
        }else if(sort.equals("high")){
            allProducts.sort(Comparator.comparing(Product::getPrice).reversed());
        }
        model.addAttribute("products", allProducts);
        model.addAttribute("maxPrice", maxPrice);
        model.addAttribute("sort", sort);
        model.addAttribute("filter", "filter");
        return "store";
    }

    @GetMapping("/store/search")
    public String storeSearch(@RequestParam("searchValue") String search, Model model) {
        List<Product> allProducts = productRepository.findAll();
        List<Product> result = new ArrayList<Product>();
        for (Product product : allProducts) {
            if (product.getName().toLowerCase().contains(search.toLowerCase()) ||
                product.getMake().toLowerCase().contains(search.toLowerCase()) ||
                product.getModel().toLowerCase().contains(search.toLowerCase()) ||
                product.getCategory().toLowerCase().contains(search.toLowerCase())
                ) {
                result.add(product);
            }
        }
        String size = Integer.toString(result.size());

        model.addAttribute("search", search);
        model.addAttribute("size", size);

        if (result.isEmpty()) {
            String error = "No items for that search";
            model.addAttribute("error", error);
            return "store";
        } else {
            model.addAttribute("products", result);
            return "store";
        }
    }

    @GetMapping("/store/category/{category}")
    public String storeCategoryFilter(@PathVariable String category, Model model) {
        Optional<List<Product>> categoryProducts = productRepository.findAllByCategory(category);
        // List<Product> allProducts = productRepository.findAll();
        model.addAttribute("products", categoryProducts.get());
        model.addAttribute("category", category);
        model.addAttribute("filter", "filter");
        return "store";
    }
}
