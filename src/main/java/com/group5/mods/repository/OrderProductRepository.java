package com.group5.mods.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.mods.model.OrderProduct;
@Repository
public interface OrderProductRepository extends JpaRepository<OrderProduct,Long> {
    
}
