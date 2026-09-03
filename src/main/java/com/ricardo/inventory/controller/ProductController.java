package com.ricardo.inventory.controller;

import com.ricardo.inventory.entity.Product;
import com.ricardo.inventory.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService service;

    @PostMapping
    public Product create(@RequestBody Product product) {
        return service.create(product);
    }

    @GetMapping
    public List<Product> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Product findById(@PathVariable Long id) {
        return service.findById(id);
    }

    @PutMapping("/{id}") // <--- Alterado para PUT eu tinha deixado GET
    public Product update(@PathVariable Long id, @RequestBody Product product) {
        return service.update(id, product);
    }

    @DeleteMapping("/{id}")
    public org.springframework.http.ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return org.springframework.http.ResponseEntity.noContent().build();
    }

    @GetMapping("/low-stock")
    public List<Product> getLowStock(@RequestParam(defaultValue = "10") Integer limit) {
        return service.findLowStock(limit);
    }

    @GetMapping("/total-value")
    public java.math.BigDecimal getTotalValue() {
        return service.getTotalValue();
    }
}