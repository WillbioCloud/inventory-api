package com.ricardo.inventory.service;

import com.ricardo.inventory.dto.OrderItemRequestDTO;
import com.ricardo.inventory.dto.OrderRequestDTO;
import com.ricardo.inventory.entity.Order;
import com.ricardo.inventory.entity.OrderItem;
import com.ricardo.inventory.entity.Product;
import com.ricardo.inventory.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.math.BigDecimal;
import java.util.List;

@Service
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final ProductService productService; // Reaproveitamos o serviço de Produtos para mexer no estoque!

    @Transactional // Garante que, se der erro em um item, nada é salvo no banco.
    public Order createOrder(OrderRequestDTO dto) {
        Order order = new Order();
        order.setCustomerName(dto.customerName());
        order.setCustomerEmail(dto.customerEmail());
        order.setDestination(dto.destination());

        BigDecimal totalAmount = BigDecimal.ZERO;

        for (OrderItemRequestDTO itemDto : dto.items()) {
            // 1. Busca o produto no banco
            Product product = productService.findById(itemDto.productId());

            // 2. Verifica se tem estoque suficiente
            if (product.getQuantity() < itemDto.quantity()) {
                throw new RuntimeException("Estoque insuficiente para o produto: " + product.getName());
            }

            // 3. Subtrai o estoque físico
            product.setQuantity(product.getQuantity() - itemDto.quantity());

            // 4. Cria o item do pedido com o preço *congelado* no momento da venda
            OrderItem orderItem = new OrderItem();
            orderItem.setProduct(product);
            orderItem.setQuantity(itemDto.quantity());
            orderItem.setUnitPrice(product.getPrice());

            BigDecimal subTotal = product.getPrice().multiply(new BigDecimal(itemDto.quantity()));
            orderItem.setSubTotal(subTotal);

            // 5. Vincula o item ao pedido usando aquele método ajudante
            order.addItem(orderItem);

            // 6. Soma ao total geral da nota
            totalAmount = totalAmount.add(subTotal);
        }

        order.setTotalAmount(totalAmount);

        // Salva o pedido (e graças ao CascadeType.ALL, os OrderItems são salvos juntos!)
        return orderRepository.save(order);
    }

    public List<Order> findAll() {
        return orderRepository.findAll();
    }
}