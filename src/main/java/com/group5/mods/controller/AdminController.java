package com.group5.mods.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.group5.mods.model.Order;
import com.group5.mods.model.OrderStatus;
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
        model.addAttribute("orderStatusEnum", OrderStatus.values());
        return "admin/admin_orders";
    }

    @PostMapping("/admin/orders/updateStatus")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateOrderStatus(@RequestParam("orderId") Long orderId, @RequestParam("orderStatus") OrderStatus orderStatus) {
        // Retrieve the order using the orderId
        Optional<Order> order = orderRepository.findById(orderId);
        // update the status in the updatedOrder
        order.get().setStatus(orderStatus);
        // Save updated order to database
        orderRepository.save(order.get());

        return "redirect:/admin/orders";
    }

    @PostMapping("/admin/users/updateRole")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateUserRole(@RequestParam("userId") Long userId, @RequestParam("userRole") String userRole) {
        // Retrieve the user using the userId
        Optional<User> user = userRepository.findById(userId);
        // update the role in the updatedOrder
        user.get().setRoles(userRole);
        // Save updated order to database
        userRepository.save(user.get());

        return "redirect:/admin/users";
    }
}
