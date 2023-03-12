package com.group5.mods.controller;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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

    @GetMapping("/admin/products/add")
    @PreAuthorize("hasRole('ADMIN')")
    public String showAddProduct(Model model) {
        Map<String, List<String>> makeModelMap = new HashMap<>();
        makeModelMap.put("Audi", Arrays.asList("A5", "Q5", "S5"));
        makeModelMap.put("BMW", Arrays.asList("Z4", "X5", "M4"));

        Set<String> makeSet = makeModelMap.keySet();
        model.addAttribute("makeModelMap", makeModelMap);
        model.addAttribute("makeSet", makeSet);
        model.addAttribute("product", new Product());
        
        return "admin/admin_addProduct";
    }

    @PostMapping("/admin/products/addProduct")
    @PreAuthorize("hasRole('ADMIN')")
    public String addProduct(@ModelAttribute("product") Product product, @RequestParam("file") MultipartFile file, BindingResult result, Model model, RedirectAttributes redirectAttributes) throws IOException {
        Map<String, List<String>> makeModelMap = new HashMap<>();
        makeModelMap.put("Audi", Arrays.asList("A5", "Q5", "S5"));
        makeModelMap.put("BMW", Arrays.asList("Z4", "X5", "M4"));
        Set<String> makeSet = makeModelMap.keySet();
        model.addAttribute("makeModelMap", makeModelMap);
        model.addAttribute("makeSet", makeSet);

        // Check if product name already exists, if it doesn then send error to thymeleaf page
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if(existingProduct.isPresent()){
            model.addAttribute("ProductNameExists", "There already exists a product with the same name, please change the name!");
            return "admin/admin_addProduct";
        }

        //Save the image file
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        Path filePath = Paths.get("src/main/resources/static/images/" + fileName);
        try (InputStream inputStream = file.getInputStream()){
            Files.copy(inputStream, filePath, StandardCopyOption.REPLACE_EXISTING);
        }

        // Set the image value in the product
        String imageURL = "/images/" + fileName;
        product.setImage(imageURL);

        // Add product to repository
        productRepository.save(product);


        redirectAttributes.addFlashAttribute("addProductSuccess", "Product successfully added!");
        return "redirect:/admin/products/add";
    }

    @PostMapping("/admin/products/updateAddProductForm")
    @PreAuthorize("hasRole('ADMIN')")
    public String updateAddProductForm(@ModelAttribute("product") Product product, BindingResult result, Model model) {
        Map<String, List<String>> makeModelMap = new HashMap<>();
        makeModelMap.put("Audi", Arrays.asList("A5", "Q5", "S5"));
        makeModelMap.put("BMW", Arrays.asList("Z4", "X5", "M4"));
        Set<String> makeSet = makeModelMap.keySet();

        List<String> models = makeModelMap.get(product.getMake());
        model.addAttribute("models", models);
        

        model.addAttribute("makeSet", makeSet);
        model.addAttribute("makeModelMap", makeModelMap);

        // Check if product name already exists, if it doesn then send error to thymeleaf page
        Optional<Product> existingProduct = productRepository.findByName(product.getName());
        if(existingProduct.isPresent()){
            model.addAttribute("ProductNameExists", "There already exists a product with the same name, please change the name!");
        }
        return "/admin/admin_addProduct";
    }
}
