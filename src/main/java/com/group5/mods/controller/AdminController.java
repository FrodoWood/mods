package com.group5.mods.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.group5.mods.model.Order;
import com.group5.mods.model.Product;
import com.group5.mods.model.User;
import com.group5.mods.repository.OrderRepository;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.UserRepository;

@Controller
public class AdminController {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private OrderRepository orderRepository;
    
    @GetMapping("/admin")
    @PreAuthorize("hasRole('ADMIN')")
    public String admin() {
        return "admin/admin_dashboard";
    }

    @GetMapping("/admin/users")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminUsers(Model model) {
        List<User> userList = new ArrayList<>();
        userList = userRepository.findAll();
        model.addAttribute("users", userList);
        return "admin/admin_users";
    }

    @GetMapping("/admin/products")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminProducts(Model model) {
        List<Product> productList = new ArrayList<>();
        productList = productRepository.findAll();
        model.addAttribute("products", productList);
        return "admin/admin_products";
    }

    @GetMapping("/admin/orders")
    @PreAuthorize("hasRole('ADMIN')")
    public String adminOrders(Model model) {
        List<Order> orderList = new ArrayList<>();
        orderList = orderRepository.findAll();
        model.addAttribute("orders", orderList);
        return "admin/admin_orders";
    }
}
