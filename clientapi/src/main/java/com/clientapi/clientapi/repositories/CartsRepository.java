package com.clientapi.clientapi.repositories;

import com.clientapi.clientapi.entities.Cart;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CartsRepository extends JpaRepository<Cart, Long> {
}
