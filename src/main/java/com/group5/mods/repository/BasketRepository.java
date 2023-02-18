package com.group5.mods.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.mods.model.Basket;
import com.group5.mods.model.User;
@Repository
public interface BasketRepository extends JpaRepository<Basket,Long> {
    Optional<Basket> findByUser(User user);
}
