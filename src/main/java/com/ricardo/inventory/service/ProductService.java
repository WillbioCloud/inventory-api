package com.ricardo.inventory.service;

import com.ricardo.inventory.entity.Product;
import com.ricardo.inventory.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ProductService {

    private final ProductRepository repository;

    public Product create(Product product) {
        return repository.save(product);
    }

    public List<Product> findAll() {
        return repository.findAll();
    }

    public Product findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public List<Product> findLowStock(Integer limit) {
        return repository.findProductsWithLowStock(limit);
    }

    public BigDecimal getTotalValue() {
        return repository.calculateTotalInventoryValue();
    }
}