package com.group5.mods.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.mods.model.Product;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {
    Optional<Product> findById(Long productId);

    Optional<Product> findByCategory(String catergory);

    Optional<Product> findByName(String name);

    Optional<Product> findByNameAndIdNot(String name, long id);

    Optional<List<Product>> findAllByCategory(String category);

    List<Product> findByMakeAndModel(String make, String model);
}
