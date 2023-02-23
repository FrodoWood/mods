package com.group5.mods.controller;

import java.util.List;
import java.util.Map;
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
import org.yaml.snakeyaml.events.Event.ID;

import com.group5.mods.model.Basket;
import com.group5.mods.model.BasketProduct;
import com.group5.mods.model.Product;
import com.group5.mods.model.SecurityUser;
import com.group5.mods.model.User;
import com.group5.mods.repository.BasketProductRepository;
import com.group5.mods.repository.BasketRepository;
import com.group5.mods.service.BasketService;
import com.group5.mods.service.ProductService;
import com.group5.mods.service.UserService;

@Controller
public class BasketController {
    @Autowired
    private ProductService productService;
    @Autowired
    private BasketRepository basketrepository;
    @Autowired
    private UserService userService;

    @Autowired
    private BasketService basketService;

    @Autowired
    private BasketProductRepository basketProductRepository;

    @GetMapping("/basket")
    public String showBasket(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        // List<BasketProduct> products = basket.get().getBasketProducts();
        // If the user already has a basket assigned, then retrieve basket from
        // database, or else create new empty basket;
        if (basket.isPresent()) {
            int total = basket.get().totalCost();
            model.addAttribute("basket", basket.get());
            model.addAttribute("products", basket.get().getBasketProducts());
            model.addAttribute("total", total);
        } else {
            model.addAttribute("basket", new Basket(user));
        }
        return "basket";
    }

    @PostMapping("/basket/add")
    public String addToBasket(@RequestParam("productId") Long productId, @RequestParam("quantity") int quantity,
            Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        Optional<Product> product = productService.findById(productId);

        // If the basket doesn't exist, create a new one for the user
        if (!basket.isPresent()) {
            Basket newBasket = new Basket(user);
            basketService.save(newBasket);
            basket = Optional.of(newBasket);
        }

        // Check if the product is in stock
        if (product.isPresent() && product.get().getStock() < quantity) {
            model.addAttribute("error", "Product is out of stock");
            model.addAttribute("product", product.get());
            return "product";
        }

        // Add the product and quantity to the basket
        basket.get().addProduct(product.get(), quantity);
        basketService.save(basket.get());

        // Retrieve the updated basket from the database
        basket = basketService.findByUser(user);
        if (basket.isPresent()) {
            model.addAttribute("basket", basket.get());
            model.addAttribute("products", basket.get().getBasketProducts());
        }

        return "redirect:/basket";

    }

    @GetMapping("basket/removeProduct/{id}")
    public String removeItem(@PathVariable Long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        Optional<Product> product = productService.findById(id);
        Optional<BasketProduct> basketproduct = basketProductRepository.findById(id);
        if (basketproduct.isPresent()) {
            basketProductRepository.delete(basketproduct.get());
        }

        // basketrepository.deleteById(id);
        // basket.get().changeQuantity(product.get(), 1);
        // basket.get().removeBasketProduct(product.get(),id);
        basketService.save(basket.get());
        model.addAttribute("basket", basket.get());
        model.addAttribute("products", basket.get().getBasketProducts());
        return "redirect:/basket";
    }

    @PostMapping("/basket/clear")
    public String clearBasket(Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        basketrepository.deleteAll();
        if (!basket.isPresent()) {
            Basket newBasket = new Basket(user);
            basketService.save(newBasket);
            basket = Optional.of(newBasket);
        }

        basket.get().getBasketProducts().clear();
        basketService.save(basket.get());
        basket = basketService.findByUser(user);
        model.addAttribute("basket", basket.get());
        model.addAttribute("products", basket.get().getBasketProducts());

        return "redirect:/basket";

    }

    @GetMapping("/basket/addQuantity/{id}")
    public String addQuantity(@PathVariable long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        Optional<Product> product = productService.findById(id);

        basket.get().addQuantity(product.get());
        basketService.save(basket.get());

        model.addAttribute("basket", basket.get());
        model.addAttribute("products", basket.get().getBasketProducts());
        return "redirect:/basket";
    }

    @GetMapping("/basket/reduce/{id}")
    public String reduceQuantity(@PathVariable long id, Model model) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        SecurityUser securityUser = (SecurityUser) authentication.getPrincipal();
        User user = securityUser.getUser();
        Optional<Basket> basket = basketService.findByUser(user);
        Optional<Product> product = productService.findById(id);

        basket.get().reduceQuantity(product.get());
        basketService.save(basket.get());

        model.addAttribute("basket", basket.get());
        model.addAttribute("products", basket.get().getBasketProducts());
        return "redirect:/basket";
    }
}
