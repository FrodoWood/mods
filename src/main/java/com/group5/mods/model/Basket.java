package com.group5.mods.model;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.ArrayList;
import java.util.List;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "basket")
public class Basket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne
    private User user;

    @OneToMany(mappedBy = "basket", cascade = CascadeType.ALL)
    private List<BasketProduct> basketProducts = new ArrayList<>();

    public Basket(User user) {
        this.user = user;
    }

    public void addProduct(Product product, int quantity) {
        BasketProduct existingItem = findBasketProductByProduct(product);
        if (existingItem != null) {
            existingItem.setQuantity(existingItem.getQuantity() + quantity);
        } else {
            BasketProduct newItem = new BasketProduct(this, product, quantity);
            basketProducts.add(newItem);
        }
    }

    public void addQuantity(Product product) {
        BasketProduct existingItem = findBasketProductByProduct(product);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() + 1;
            if (newQuantity >= 0) {
                existingItem.setQuantity(newQuantity);
            } else {
                basketProducts.remove(existingItem);
            }
        }
    }

    public void reduceQuantity(Product product) {
        BasketProduct existingItem = findBasketProductByProduct(product);
        if (existingItem != null) {
            int newQuantity = existingItem.getQuantity() - 1;
            if (newQuantity >= 0) {
                existingItem.setQuantity(newQuantity);
            } else {
                basketProducts.remove(existingItem);
            }
        }
    }

    // public void removeBasketProduct(Product product,long id){

    // }
    public void clear() {
        basketProducts.clear();
    }

    private BasketProduct findBasketProductByProduct(Product product) {
        for (BasketProduct basketProduct : basketProducts) {
            if (basketProduct.getProduct().equals(product)) {
                return basketProduct;
            }
        }
        return null;
    }

    public BigDecimal totalCost() {
        BigDecimal total = BigDecimal.ZERO;
        for (BasketProduct basketProduct : basketProducts) {
            BigDecimal price = basketProduct.getProduct().getPrice();
            BigDecimal quantity = BigDecimal.valueOf(basketProduct.getQuantity());
            total = total.add(price.multiply(quantity));
        }
        return total.setScale(2, RoundingMode.HALF_UP);
    }
}
