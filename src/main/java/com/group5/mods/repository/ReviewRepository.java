package com.group5.mods.repository;

import java.util.List;

import org.springframework.data.domain.PageRequest;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.mods.model.Order;
import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.model.User;
@Repository
public interface ReviewRepository extends JpaRepository<Review, Long> {
   List<Review> findByUser(User user);

   List<Review> findByProduct(Product product);

   List<Review> findAllByOrderByDateCreatedDesc(PageRequest pageRequest);

   List<Review> findAllByOrderByDateCreatedDesc(); 
}
