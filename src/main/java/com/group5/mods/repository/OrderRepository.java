package com.group5.mods.repository;

import java.util.Collection;
import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.group5.mods.model.Order;
import com.group5.mods.model.User;
@Repository
public interface OrderRepository extends JpaRepository<Order, Long> {
    List<Order> findByUser(User user);

    List<Order> findAllByOrderByDateCreatedDesc();

    List<Order> findAllByUserOrderByDateCreatedDesc(User user);

    Optional<Order> findById(Long id);
}
