package com.clientapi.clientapi.controllers;

import com.clientapi.clientapi.entities.Product;
import com.clientapi.clientapi.services.ProductsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/v1/products")
public class ProductsController {

    @Autowired
    private ProductsService service;

    @PostMapping()
    public void saveClient(@RequestBody Product product) {
        try {
            service.saveProduct(product);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("CREATE ERROR");
        }
    }

    @GetMapping()
    public List<Product> readProduct() {
        try {
            return service.readProduct();
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ALL PRODUCTS ERROR");
        }
    }

    @GetMapping("/{id}")
    public Optional<Product> readOneProduct(@PathVariable Long id) {
        try {
            return service.readOneProduct(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("READ ONE PRODUCT ERROR");
        }
    }

    @DeleteMapping("/{id}")
    public void deleteAllClients(@PathVariable("id") Long id) {
        try {
            service.deleteProduct(id);
        } catch (Exception exception) {
            System.out.println(exception);
            throw new RuntimeException("DELETE ALL PRODUCTS ERROR");
        }
    }
    @PutMapping("/{id}")
    public ResponseEntity<Product> updateProduct(@PathVariable("id") Long id, @RequestBody Product updatedProduct) {
        try {
            // Verificar si el producto existe en la base de datos
            Optional<Product> existingProductOptional = service.readOneProduct(id);

            if (existingProductOptional.isPresent()) {
                Product existingProduct = existingProductOptional.get();

                // Actualizar los campos del producto existente con los datos del producto actualizado
                existingProduct.setDescription(updatedProduct.getDescription());
                existingProduct.setCode(updatedProduct.getCode());
                existingProduct.setStock(updatedProduct.getStock());
                existingProduct.setPrice(updatedProduct.getPrice());

                // Guardar el producto actualizado en la base de datos
                Product savedProduct = service.saveProduct(existingProduct);

                return ResponseEntity.ok(savedProduct);
            } else {
                return ResponseEntity.notFound().build(); // Retorna 404 si no se encuentra el producto
            }
        } catch (Exception e) {
            System.out.println(e);
            throw new RuntimeException("Update Product Error: " + e.getMessage());
        }
    }

}
