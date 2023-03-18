package com.group5.mods.controller;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group5.mods.model.Order;
import com.group5.mods.model.OrderProduct;
import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.model.User;
import com.group5.mods.repository.OrderRepository;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;

@Controller
public class ReviewController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;

    @PostMapping("/products/{productId}/reviews")
    public String createReview(@PathVariable ("productId") Long productId, @AuthenticationPrincipal UserDetails userDetails, 
                                @RequestParam("rating") Float rating, @RequestParam("comment") String comment, 
                                RedirectAttributes redirectAttributes){
        // Check if the user is authenticated
        if(userDetails == null){
            redirectAttributes.addFlashAttribute("error", "You need to be logged to leave a review, please log in.");
            return "redirect:/login";
        }

        // Get the current user's orders
        User user = (User) userDetails;
        List<Order> orders = orderRepository.findByUser(user);

        // Check if the user has ordered the product
        boolean hasOrderedProduct = false;
        for(Order order : orders){
            for(OrderProduct orderProduct : order.getOrderProducts()){
                if(orderProduct.getProduct().getId() == productId){
                    hasOrderedProduct = true;
                    break;
                }
            }
            if(hasOrderedProduct){
                break;
            }
        }

        // Send error message if user has not ordered product and is trying to leave a review
        if(!hasOrderedProduct){
            redirectAttributes.addFlashAttribute("error", "You have to purchase the item to leave a review.");
            return "redirect:/store";
        }

        // If all the checks are passed then the user can leave a review
        // Creating the review
        Optional<Product> product = productRepository.findById(productId);
        Review newReview = new Review(user, product.get(), comment, false, LocalDateTime.now(), rating);
        reviewRepository.save(newReview);

        redirectAttributes.addFlashAttribute("success", "Thank you for submitting a review.");
        return "redirect:/products/" + productId;
    }
}
