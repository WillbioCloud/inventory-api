package com.ricardo.inventory.entity;

import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders") // "Order" é uma palavra reservada no SQL, então usamos "orders"
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String customerName;
    private String customerEmail;
    private String destination;

    @Enumerated(EnumType.STRING)
    private OrderStatus status = OrderStatus.PROCESSING;

    private BigDecimal totalAmount;

    @CreationTimestamp
    private LocalDateTime createdAt;

    // Um pedido tem vários itens. Se eu apagar o pedido (CascadeType.ALL), apaga os itens dele.
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<OrderItem> items = new ArrayList<>();

    // Método auxiliar para facilitar a adição de itens no Service futuramente
    public void addItem(OrderItem item) {
        items.add(item);
        item.setOrder(this);
    }
}