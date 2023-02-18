package com.group5.mods.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.group5.mods.model.Basket;
import com.group5.mods.model.User;
import com.group5.mods.repository.BasketRepository;

@Service
public class BasketService {
    @Autowired
    BasketRepository basketRepository;

    public Optional<Basket> findByUser(User user){
        return basketRepository.findByUser(user);
    }

    public Basket save(Basket basket){
        Optional<Basket> existingBasket = basketRepository.findByUser(basket.getUser());
        if(existingBasket.isPresent()){
            Basket updatedBasket = existingBasket.get();
            updatedBasket.setBasketProducts(basket.getBasketProducts());
            return basketRepository.save(updatedBasket);
        }
        else{
            return basketRepository.save(basket);
        }
    }
}
