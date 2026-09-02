package com.ricardo.inventory.controller;

import com.ricardo.inventory.dto.OrderRequestDTO;
import com.ricardo.inventory.entity.Order;
import com.ricardo.inventory.service.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService service;

    @PostMapping
    public Order create(@RequestBody OrderRequestDTO dto) {
        return service.createOrder(dto);
    }

    @GetMapping
    public List<Order> findAll() {
        return service.findAll();
    }
}