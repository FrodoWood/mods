package com.group5.mods.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.mods.model.Product;
import com.group5.mods.repository.ProductRepository;

@Service
public class ProductService {
    @Autowired
    ProductRepository productRepository;

    public Optional<Product> findById(Long productId){
        return productRepository.findById(productId);
    }
}
