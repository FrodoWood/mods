package com.group5.mods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.group5.mods.model.BasketProduct;
import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;
import com.group5.mods.service.ProductService;

@Controller
public class ProductController extends BaseController {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ProductService productService;
    @Autowired
    private ReviewRepository reviewRepository;

    @GetMapping("/product/{id}")
public String getProduct(@PathVariable Long id, Model model) {
    Optional<Product> product = productService.findById(id);
    List<Review> reviews = reviewRepository.findByProduct(product.get());
    List<Review> displayedReviews = new ArrayList<>();
    for(Review review : reviews){
        if(!review.getHidden()){
            displayedReviews.add(review);
        }
    }
    model.addAttribute("product", product.get());
    model.addAttribute("reviews", displayedReviews);
    model.addAttribute("basketproduct", new BasketProduct()); // add new empty object
    return "product";
}

}
