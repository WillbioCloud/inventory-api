package com.ricardo.inventory.service;

import com.ricardo.inventory.entity.InventoryMovement;
import com.ricardo.inventory.entity.MovementType;
import com.ricardo.inventory.entity.Product;
import com.ricardo.inventory.repository.InventoryMovementRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryMovementService {

    private final InventoryMovementRepository movementRepository;
    private final ProductService productService; // Injetamos o serviço do Produto!

    @Transactional
    public InventoryMovement registerMovement(InventoryMovement movement) {
        // 1. Busca o produto existente no banco
        Product product = productService.findById(movement.getProduct().getId());

        // 2. Aplica a regra de estoque
        if (movement.getType() == MovementType.ENTRY) {
            product.setQuantity(product.getQuantity() + movement.getQuantity());
        } else if (movement.getType() == MovementType.EXIT) {
            if (product.getQuantity() < movement.getQuantity()) {
                throw new RuntimeException("Estoque insuficiente! Saldo atual: " + product.getQuantity());
            }
            product.setQuantity(product.getQuantity() - movement.getQuantity());
        }

        // 3. Vincula o produto atualizado e salva o histórico
        movement.setProduct(product);
        return movementRepository.save(movement);
    }

    public List<InventoryMovement> findAll() {
        return movementRepository.findAll();
    }
}