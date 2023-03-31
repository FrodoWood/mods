package com.group5.mods.model;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.math.BigDecimal;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class ProductTest {

    private Product product;

    @BeforeEach
    public void createProduct(){
        product = new Product(4.5f, "TestName1", "TestDescription1", "TestMake1", "TestModel1", "TestCategory1", new BigDecimal(10.00), 5, "/images/test.jpg");  
    }

    @Test
    void testIncreaseStockBy() {
        int initialStock = product.getStock();
        int amount = 5;
        int expectedStock = initialStock + amount;
        int newStock = product.increaseStockBy(amount);

        assertEquals(expectedStock, newStock, "Stock should be increased by 5");
    }

    @Test
    void testReduceStockBy() {
        int initialStock = product.getStock();
        int amount = 5;
        int expectedStock = initialStock - amount;
        int newStock = product.reduceStockBy(amount);

        assertEquals(expectedStock, newStock, "Stock should be reduced by 5");
    }

    @Test
    public void testGetters(){
        assertEquals(4.5f, product.getRating());
        assertEquals("TestName1", product.getName());
        assertEquals("TestDescription1", product.getDescription());
        assertEquals("TestMake1", product.getMake());
        assertEquals("TestModel1", product.getModel());
        assertEquals("TestCategory1", product.getCategory());
        assertEquals(new BigDecimal(10.00), product.getPrice());
        assertEquals(5, product.getStock());
        assertEquals("/images/test.jpg", product.getImage());
    }

    @Test
    public void testSetters() {
        product.setRating(3.5f);
        product.setName("NewName");
        product.setDescription("NewDescription");
        product.setMake("NewMake");
        product.setModel("NewModel");
        product.setCategory("NewCategory");
        product.setPrice(BigDecimal.valueOf(200.0));
        product.setStock(20);
        product.setImage("new-test-image.jpg");

        assertEquals(3.5f, product.getRating());
        assertEquals("NewName", product.getName());
        assertEquals("NewDescription", product.getDescription());
        assertEquals("NewMake", product.getMake());
        assertEquals("NewModel", product.getModel());
        assertEquals("NewCategory", product.getCategory());
        assertEquals(BigDecimal.valueOf(200.0), product.getPrice());
        assertEquals(20, product.getStock());
        assertEquals("new-test-image.jpg", product.getImage());
    }
}
