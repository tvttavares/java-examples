package com.tavares.httprequests.controller;

import com.tavares.httprequests.domain.Product;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
public class ProductController {

    private List<Product> products = new ArrayList<>();

    public ProductController(List<Product> products) {
        this.products = products;

        populateList(products);
    }

    private void populateList(List<Product> products) {
        products.add(new Product("Television", "Samsung", 1145.67, "S001"));
        products.add(new Product("Washing Machine", "LG", 114.67, "L001"));
        products.add(new Product("Laptop", "Apple", 11453.67, "A001"));
    }

    @GetMapping(value = "/products/{id}")
    public @ResponseBody
    Product fetchProducts(@PathParam("id") String productId) {
        return products.get(1);
    }

    @GetMapping("/products")
    public List<Product> fetchProducts() {
        return products;
    }

    @PostMapping("/products")
    public ResponseEntity<String> createProduct(@RequestBody Product product) {
        // Create product with ID;
        String productID = UUID.randomUUID().toString();
        product.setId(productID);
        products.add(product);

        return ResponseEntity.ok().body(
                "{\"productID\":\"" + productID + "\"}");
    }

    @PostMapping("/productsFullResponse")
    public ResponseEntity<Product> createProductFullResponse(@RequestBody Product product) {
        // Create product with ID;
        String productID = UUID.randomUUID().toString();
        product.setId(productID);
        products.add(product);

        return ResponseEntity.ok().body(product);
    }

    @PutMapping("/products")
    public ResponseEntity<String> updateProduct(@RequestBody Product product) {
        products.set(1, product);
        // Update product. Return success or failure without response body
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/products")
    public ResponseEntity<String> deleteProduct(@RequestBody Product product) {
        products.remove(1);
        // Update product. Return success or failure without response body
        return ResponseEntity.ok().build();
    }

    @GetMapping("/products/error")
    public ResponseEntity<Product> fetchProductWithError() {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
    }
}