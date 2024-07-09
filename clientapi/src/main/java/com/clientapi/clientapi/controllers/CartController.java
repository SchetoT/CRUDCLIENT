package com.clientapi.clientapi.controllers;

import com.clientapi.clientapi.entities.Cart;
import com.clientapi.clientapi.services.CartsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/carts")
public class CartController {

    @Autowired
    private CartsService cartService;

    @PostMapping()
    public ResponseEntity<Cart> addItemToCart(@RequestBody Cart cartItem) {
        Cart savedCartItem = cartService.addItemToCart(cartItem);
        return new ResponseEntity<>(savedCartItem, HttpStatus.CREATED);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> removeItemFromCart(@PathVariable("id") Long cartItemId) {
        cartService.removeItemFromCart(cartItemId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping()
    public ResponseEntity<List<Cart>> getAllCart() {
        List<Cart> cart = cartService.getAllCart();
        return ResponseEntity.ok(cart);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cart> getCartItemById(@PathVariable("id") Long cartItemId) {
        Optional<Cart> cartItem = cartService.getCartItemById(cartItemId);
        return cartItem.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }
    @PutMapping("/{id}")
    public ResponseEntity<Cart> updateCartItem(@PathVariable("id") Long cartItemId, @RequestBody Cart updatedCartItem) {
        Optional<Cart> existingCartItemOptional = cartService.getCartItemById(cartItemId);

        if (existingCartItemOptional.isPresent()) {
            Cart existingCartItem = existingCartItemOptional.get();

            // Actualizar los campos necesarios del carrito existente con los datos del carrito actualizado
            existingCartItem.setAmount(updatedCartItem.getAmount());
            existingCartItem.setPrice(updatedCartItem.getPrice());
            existingCartItem.setClient_id(updatedCartItem.getClient_id());
            existingCartItem.setProduct_id(updatedCartItem.getProduct_id());

            // Guardar el carrito actualizado en la base de datos
            Cart updatedCartItemInDb = cartService.addItemToCart(existingCartItem);
            return ResponseEntity.ok(updatedCartItemInDb);
        } else {
            return ResponseEntity.notFound().build();
        }
    }


}