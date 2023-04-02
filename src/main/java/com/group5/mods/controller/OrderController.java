package com.group5.mods.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group5.mods.model.Basket;
import com.group5.mods.model.BasketProduct;
import com.group5.mods.model.Order;
import com.group5.mods.model.OrderStatus;
import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.repository.BasketRepository;
import com.group5.mods.repository.OrderRepository;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.service.BasketService;
import com.group5.mods.service.OrderService;
import com.group5.mods.service.UserService;

@Controller
public class OrderController extends BaseController {
    @Autowired
    private OrderService orderService;

    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private OrderRepository orderRepository;

    @PostMapping("/checkout")
    public String placeOrder(Model model, RedirectAttributes redirectAttributes){
        // Getting the authenticated user
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        // Getting basket, basket products, and creating a new order object with the user and basket products
        Optional<Basket> basket = basketService.findByUser(user);
        List<BasketProduct> basketProducts = basket.get().getBasketProducts();
        // Check if basket is empty, don't allow to checkout with empty basket
        if(basketProducts.isEmpty()){
            return "redirect:/basket";
        }

        // Checking stock before allowing to make an order
        for(BasketProduct basketProduct : basketProducts){
            if(basketProduct.getQuantity() > basketProduct.getProduct().getStock()){
                // model.addAttribute("errorStock", "Not enough products in stock");
                redirectAttributes.addFlashAttribute("errorStock", basketProduct.getProduct().getName());
                redirectAttributes.addFlashAttribute("errorProduct", basketProduct);
                return "redirect:/basket";
            }
        }
        
        Order order = orderService.createOrder(user, basketProducts);

        // Reduce the quantity for each product inside the basket
        for(BasketProduct basketProduct : basketProducts){
            productRepository
            .findById(basketProduct.getProduct().getId()).get()
            .reduceStockBy(basketProduct.getQuantity());
            ;
        }

        // Removing everything from basked since order was created, and saving new empty basket to the database
        basketRepository.deleteAll();
        basketProducts.clear();
        basketService.save(basket.get());
        redirectAttributes.addFlashAttribute("orderSuccess", "Your order has been successfully placed. See details below.");
        return "redirect:/orders";
    }

    @GetMapping("/orders")
    public String getOrders(Model model){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        List<Order> orders = orderRepository.findAllByUserOrderByDateCreatedDesc(user);
        model.addAttribute("orders", orders);
        return "orders";
    }
    
    @GetMapping("/orders/{id}/cancel")
    public String cancelOrder(Model model, @PathVariable("id") Long orderId, RedirectAttributes redirectAttributes){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Order> order = orderRepository.findById(orderId);
        if(!order.isPresent()){
            redirectAttributes.addFlashAttribute("orderNotFound", "Order not found!");
            return "redirect:/orders";
        }
        order.get().setStatus(OrderStatus.CANCELLED);
        orderRepository.save(order.get());
        redirectAttributes.addFlashAttribute("orderCancelSuccess",  "Order #" + order.get().getId() + " successfully cancelled!");
        return "redirect:/orders";
    }
}
