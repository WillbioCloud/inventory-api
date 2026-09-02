package com.ricardo.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "customers")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Customer {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String company;
    private String contact;
    private String email;
    private String phone;

    // Status padrão ao criar um cliente
    private String status = "Ativo";

    // Métricas para o Dashboard (iniciam zeradas)
    private Integer totalOrders = 0;
    private BigDecimal ltv = BigDecimal.ZERO;

    @CreationTimestamp
    private LocalDateTime createdAt;
}