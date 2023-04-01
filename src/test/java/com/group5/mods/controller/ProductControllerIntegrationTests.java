package com.group5.mods.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ActiveProfiles;
import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.model.User;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;
import com.group5.mods.repository.UserRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/mods_test",
    "spring.datasource.username=root",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.hibernate.ddl-auto=create",
})
@ActiveProfiles("test")
public class ProductControllerIntegrationTests {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private ReviewRepository reviewRepository;

    @Autowired
    private UserRepository userRepository;

    @Test
    public void testGetProduct() {
        Product product = new Product(4.5f, "TestName1", "TestDescription1"
                                        , "TestMake1", "TestModel1", "TestCategory1"
                                        , new BigDecimal(10.00), 5, "/images/test.jpg");
        productRepository.save(product);

        ResponseEntity<String> response = restTemplate.getForEntity("/product/" + product.getId(), String.class);
        assertTrue(response.getBody().contains(product.getName()));
        assertTrue(response.getBody().contains(product.getDescription()));
        System.out.println(response.getBody());
    }

    @Test
    public void testCreateAndRetrieveReview() {
        // Create a user and save to the database
        PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
        User user = new User("TestName", "TestUsername", "test@gmail.com", passwordEncoder.encode("testPassword"), "USER_ROLE");
        userRepository.save(user);

        // create a new product
        Product product = new Product(4.5f, "Test Product", "Test Description", "Test Make", "Test Model",
                                        "Test Category", new BigDecimal(10.00), 5, "/images/test.jpg");
        productRepository.save(product);

        // create a new review
        Review review = new Review(user, product, "Test Review", false, LocalDateTime.now(), 3.5f);
        reviewRepository.save(review);

        // retrieve the product with reviews
        ResponseEntity<String> response = restTemplate.getForEntity("/product/" + product.getId(), String.class);
        String responseBody = response.getBody();
        assertTrue(responseBody.contains(product.getName()));
        assertTrue(responseBody.contains(product.getDescription()));
        assertTrue(responseBody.contains(Float.toString(product.getRating())));
    }



}
