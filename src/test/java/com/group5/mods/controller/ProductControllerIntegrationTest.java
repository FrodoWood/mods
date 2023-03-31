package com.group5.mods.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.util.Arrays;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.group5.mods.model.Product;
import com.group5.mods.model.Review;
import com.group5.mods.repository.ProductRepository;
import com.group5.mods.repository.ReviewRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT, properties = {
    "spring.datasource.url=jdbc:mysql://localhost:3306/mods_test",
    "spring.datasource.username=root",
    "spring.datasource.password=",
    "spring.datasource.driver-class-name=com.mysql.cj.jdbc.Driver",
    "spring.jpa.hibernate.ddl-auto=create-drop",
})
@ActiveProfiles("test")
public class ProductControllerIntegrationTest {
    
    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private ProductRepository productRepository;


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
}
