package com.clientapi.clientapi.repositories;

import com.clientapi.clientapi.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductsRepository extends JpaRepository<Product, Long>{

    }

