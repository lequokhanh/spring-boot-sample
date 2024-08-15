package com.example.demo.services;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.entities.CartItem;
import com.example.demo.entities.OrderItem;
import com.example.demo.entities.User;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.OrderItemRepository;

@Service
public class PurchaseService {

    OrderItemRepository orderItemRepository;
    CartItemRepository cartItemRepository;

    public PurchaseService(OrderItemRepository orderItemRepository, CartItemRepository cartItemRepository) {
        this.orderItemRepository = orderItemRepository;
        this.cartItemRepository = cartItemRepository;
    }

    public void purchase(User user) {
        Optional<CartItem> cartItem = cartItemRepository.findByUserId(user.getId());

        List<CartItem> cartItems = cartItem.map(Collections::singletonList).orElse(Collections.emptyList());

        for (CartItem item : cartItems) {
            OrderItem orderItem = new OrderItem().product(item.getProduct()).quantity(item.getQuantity()).user(user);

            orderItemRepository.save(orderItem);
            cartItemRepository.delete(item);
        }
    }

    public List<OrderItem> getOrders(User user) {
        Optional<OrderItem> orderItem = orderItemRepository.findByUserId(user.getId());

        return orderItem.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    public double getTotal(User user) {
        Optional<OrderItem> orderItem = orderItemRepository.findByUserId(user.getId());

        return orderItem.map(item -> item.getProduct().getPrice() * item.getQuantity()).orElse(0.0);
    }
}
