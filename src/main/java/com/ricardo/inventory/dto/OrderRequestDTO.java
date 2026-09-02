package com.ricardo.inventory.dto;

import java.util.List;

public record OrderRequestDTO(
        String customerName,
        String customerEmail,
        String destination,
        List<OrderItemRequestDTO> items
) {}