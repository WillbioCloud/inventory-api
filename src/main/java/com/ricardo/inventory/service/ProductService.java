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

    public Product update(Long id, Product updateData) {
        // 1. Busca o produto existente
        Product existingProduct = repository.findById(id)
                .orElseThrow(() -> new RuntimeException("Produto não encontrado com o ID: " + id));

        // 2. Atualiza os dados do produto
        existingProduct.setName(updateData.getName());
        existingProduct.setSku(updateData.getSku());
        existingProduct.setPrice(updateData.getPrice());
        existingProduct.setQuantity(updateData.getQuantity());
        existingProduct.setCategory(updateData.getCategory());
        existingProduct.setImagemUrl(updateData.getImagemUrl());

        // 3. Salva e devolve o produto atualizado
        return repository.save(existingProduct);
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