package com.ricardo.inventory.repository;

import com.ricardo.inventory.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.util.List;

@Repository
public interface ProductRepository extends JpaRepository<Product, Long> {

    // Relatório 1: Produtos com estoque baixo
    @Query("SELECT p FROM Product p WHERE p.quantity <= :limit")
    List<Product> findProductsWithLowStock(@Param("limit") Integer limit);

    // Relatório 2: Valor total de dinheiro no estoque
    @Query("SELECT SUM(p.quantity * p.price) FROM Product p")
    BigDecimal calculateTotalInventoryValue();
}