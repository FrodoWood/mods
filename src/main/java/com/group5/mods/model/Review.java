package com.group5.mods.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.DecimalMax;
import jakarta.validation.constraints.DecimalMin;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Table(name = "reviews")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @Column(nullable = false)
    private String comment;

    private boolean hidden;

    private LocalDateTime dateCreated;

    @Column(nullable = false)
    @DecimalMin("0.0")
    @DecimalMax("5.0")
    private float rating;

    public Review(User user, Product product, String comment, boolean hidden, LocalDateTime dateCreated, float rating){
        this.user = user;
        this.product = product;
        this.comment = comment;
        this.hidden = hidden;
        this.dateCreated = dateCreated;
        this.rating = rating;
    }
    public boolean getHidden(){
        return this.hidden;
    }
    public float getRating(){
        return this.rating;
    }
}
