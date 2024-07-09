package com.clientapi.clientapi.services;

import com.clientapi.clientapi.entities.Cart;
import com.clientapi.clientapi.repositories.CartsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CartsService {

    @Autowired
    private CartsRepository cartRepository;

    public Cart addItemToCart(Cart cartItem) {
        return cartRepository.save(cartItem);
    }

    public void removeItemFromCart(Long cartItemId) {
        cartRepository.deleteById(cartItemId);
    }

    public List<Cart> getAllCart() {
        return cartRepository.findAll();
    }

    public Optional<Cart> getCartItemById(Long cartItemId) {
        return cartRepository.findById(cartItemId);
    }


}