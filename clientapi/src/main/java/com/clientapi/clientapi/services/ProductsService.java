package com.clientapi.clientapi.services;
import com.clientapi.clientapi.entities.Product;
import com.clientapi.clientapi.repositories.ProductsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
@Service
public class ProductsService {
    @Autowired
    private ProductsRepository repository;

    public Product saveProduct(Product product) {
        repository.save(product);
        return product;
    }
    public List<Product> readProduct(){
        return repository.findAll();
    }
    public Optional<Product> readOneProduct(Long id){
        return repository.findById(id);
    }
    //opcional porque no siempre puede encontrar el cliente
    //devuelve null si no lo encuentra
    public void deleteProduct(Long id){
        repository.deleteById(id);
    }
}

