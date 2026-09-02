package com.ricardo.inventory.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

import java.math.BigDecimal;

@Entity
@Table(name = "order_items")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // A qual pedido este item pertence
    @ManyToOne
    @JoinColumn(name = "order_id")
    @JsonIgnore // Evita loop infinito na hora de devolver o JSON para o React
    private Order order;

    // Qual produto foi comprado
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    private Integer quantity;

    // O preço do produto no momento exato da venda
    private BigDecimal unitPrice;

    private BigDecimal subTotal;
}