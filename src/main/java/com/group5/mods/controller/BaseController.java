package com.group5.mods.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;

import com.group5.mods.model.Basket;
import com.group5.mods.model.BasketProduct;
import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.service.BasketService;

@Controller
public class BaseController {
    @Autowired
    private BasketService basketService;

    @ModelAttribute("basketCount")
    public Integer basketCount(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            Optional<Basket> basket = basketService.findByUser(user);
            int numberOfBasketProducts = 0;
            if(basket.isPresent()){
                for(BasketProduct basketProduct : basket.get().getBasketProducts()){
                    numberOfBasketProducts += basketProduct.getQuantity();
                }
            }
            return numberOfBasketProducts;
        }
        return null;
    }

    @ModelAttribute("loggedUser")
    public String loggedUser(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            return user.getName();
        }
        return null;
    }
    @ModelAttribute("userRole")
    public String userRole(Authentication authentication){
        if (authentication != null && authentication.isAuthenticated()) {
            SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
            User user = securityUser.getUser();
            return user.getRoles();
        }
        return null;
    }
}
