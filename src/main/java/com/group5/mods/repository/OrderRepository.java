package com.group5.mods.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.group5.mods.model.Order;
import com.group5.mods.model.User;

public interface OrderRepository extends JpaRepository<Order, Long> {
    Optional<Order> findByUsername(String username);

    List<Order> findByUser(User user);
}
