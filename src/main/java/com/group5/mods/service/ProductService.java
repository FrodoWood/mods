package com.group5.mods.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;
    @Autowired
    private ReviewRepository reviewRepository;

    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }

    public void updateProductRating(Long productId) {
        Optional<Product> optionalProduct = productRepository.findById(productId);
        if(optionalProduct.isPresent()) {
            Product product = optionalProduct.get();
            List<Review> reviews = reviewRepository.findByProduct(product);
            if(!reviews.isEmpty()) {
                float ratingSum = 0;
                for(Review review : reviews) {
                    ratingSum += review.getRating();
                }
                float averageRating = ratingSum / reviews.size();
                product.setRating(averageRating);
                productRepository.save(product);
            }
        }
    }
}
