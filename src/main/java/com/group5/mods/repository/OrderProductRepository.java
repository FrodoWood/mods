package com.group5.mods.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.mods.model.OrderProduct;

public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    
}
