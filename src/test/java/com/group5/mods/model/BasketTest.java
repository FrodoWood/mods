package com.group5.mods.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.math.BigDecimal;
import java.math.RoundingMode;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class BasketTest {

    private Basket basket;
    private Product product = new Product(4.5f, "TestName1", "TestDescription1"
                                            , "TestMake1", "TestModel1", "TestCategory1"
                                            , new BigDecimal(10.00), 5, "/images/test.jpg");

    @BeforeEach
    void createBasketAndBasketProduct(){
        basket = new Basket();
    }

    @Test
    void testAddProductWithNewProduct() {
        assertTrue(basket.getBasketProducts().isEmpty());
        basket.addProduct(product, 3);
        assertFalse(basket.getBasketProducts().isEmpty());
    }

    @Test
    void testAddProductWithExistingProduct(){
        basket.addProduct(product, 3);
        assertTrue(basket.getBasketProducts().size() == 1);
        basket.addProduct(product, 1);
        assertTrue(basket.getBasketProducts().size() == 1);
        assertTrue(basket.getBasketProducts().get(0).getQuantity() == 4);
    }

    @Test
    void testAddQuantity() {
        basket.addProduct(product, 1);
        assertEquals(1, basket.getBasketProducts().get(0).getQuantity());
        basket.addQuantity(product);
        assertEquals(2, basket.getBasketProducts().get(0).getQuantity());
    }

    @Test
    void testReduceQuantity() {
        basket.addProduct(product, 1);
        assertEquals(1, basket.getBasketProducts().get(0).getQuantity());
        basket.reduceQuantity(product);
        assertEquals(0, basket.getBasketProducts().get(0).getQuantity());
    }

    @Test
    void testClear() {
        basket.addProduct(product, 3);
        assertFalse(basket.getBasketProducts().isEmpty());
        basket.clear();
        assertTrue(basket.getBasketProducts().isEmpty());

    }

    @Test
    void testTotalCost() {
        Product product2 = new Product(4.5f, "TestName1", "TestDescription1"
                                        , "TestMake1", "TestModel1", "TestCategory1"
                                        , new BigDecimal(50.00), 5, "/images/test.jpg");
        basket.addProduct(product, 1); //Cost = 10*1 = £10
        basket.addProduct(product2, 2); // Cost = 50*2 = £100
        assertEquals(new BigDecimal(110.00).setScale(2, RoundingMode.HALF_UP), basket.totalCost());
    }

}
