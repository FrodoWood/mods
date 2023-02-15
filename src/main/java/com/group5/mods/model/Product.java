package com.group5.mods.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false, scale = 5)
    private float rating;

    @Column(nullable = false)
    private String description;

    @Column(nullable = false)
    private String model;

    @Column(nullable = false)
    private String make;

    @Column(nullable = false)
    private String category;

    @Column(nullable = false)
    private int price;

    public Product(float Rating, String name, String description, String make, String model, String category,
            int price) {
        this.rating = Rating;
        this.name = name;
        this.description = description;
        this.make = make;
        this.price = price;
        this.model = model;
        this.category = category;
    }
}
