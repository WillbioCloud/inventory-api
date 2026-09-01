package com.ricardo.inventory.controller;

import com.ricardo.inventory.entity.InventoryMovement;
import com.ricardo.inventory.service.InventoryMovementService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movements")
@RequiredArgsConstructor
public class InventoryMovementController {

    private final InventoryMovementService service;

    @PostMapping
    public InventoryMovement create(@RequestBody InventoryMovement movement) {
        return service.registerMovement(movement);
    }

    @GetMapping
    public List<InventoryMovement> findAll() {
        return service.findAll();
    }
}