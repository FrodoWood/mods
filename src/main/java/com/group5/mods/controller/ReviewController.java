package com.group5.mods.controller;

import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.group5.mods.model.Order;
import com.group5.mods.model.OrderProduct;
import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.repository.OrderRepository;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;
import com.group5.mods.service.ProductService;

@Controller
public class ReviewController extends BaseController {
    @Autowired
    private ReviewRepository reviewRepository;
    @Autowired
    private OrderRepository orderRepository;
    @Autowired
    private ProductRepository productRepository;
    @Autowired
    private ProductService productService;

    @PostMapping("/product/{productId}/createReview")
    public String createReview(@PathVariable ("productId") Long productId, 
                                @RequestParam("rating") Float rating, @RequestParam("comment") String comment, 
                                RedirectAttributes redirectAttributes){
        // Check if the user is authenticated
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
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
            redirectAttributes.addFlashAttribute("errorReview", "You have to purchase the item to leave a review.");
            return "redirect:/product/" + productId;
        }

        // If all the checks are passed then the user can leave a review
        // Creating the review
        Optional<Product> product = productRepository.findById(productId);
        Review newReview = new Review(user, product.get(), comment, false, LocalDateTime.now(), rating);
        reviewRepository.save(newReview);

        // Update the product rating
        productService.updateProductRating(productId);

        redirectAttributes.addFlashAttribute("successReview", "Thank you for submitting a review.");
        return "redirect:/product/" + productId;
    }

    @GetMapping("/product/{productId}/review")
    public String showReviewForm(@PathVariable ("productId") Long productId,Model model){

        model.addAttribute("productId", productId);
        return "writeReview";
    }

}
