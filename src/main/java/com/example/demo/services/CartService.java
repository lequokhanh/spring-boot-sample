package com.example.demo.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import com.example.demo.dtos.AddCartItemBodyDto;
import com.example.demo.dtos.UpdateCartItemBodyDto;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.CartItemRepository;
import com.example.demo.repositories.ProductRepository;

@Service
public class CartService {
    private CartItemRepository cartItemRepository;
    private ProductRepository productRepository;

    public CartService(CartItemRepository cartItemRepository, ProductRepository productRepository) {
        this.cartItemRepository = cartItemRepository;
        this.productRepository = productRepository;
    }

    public List<CartItem> getCartItems(UUID userId) {
        Optional<CartItem> cartItem = cartItemRepository.findByUserId(userId);

        return cartItem.map(Collections::singletonList).orElse(Collections.emptyList());
    }

    public void updateItem(UpdateCartItemBodyDto body, UUID userId) {
        UUID productId = body.getProductId();
        int newQuantity = body.getNewQuantity();

        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(userId, productId);

        if (newQuantity == 0) {
            cartItem.ifPresent(cartItemRepository::delete);
        } else {
            cartItem.ifPresent(item -> {
                item.setQuantity(newQuantity);
                cartItemRepository.save(item);
            });
        }
    }

    public void addItem(AddCartItemBodyDto body, User user) {
        UUID productId = body.getProductId();
        int quantity = body.getQuantity();

        Optional<CartItem> cartItem = cartItemRepository.findByUserIdAndProductId(user.getId(), productId);

        if (cartItem.isPresent()) {
            CartItem item = cartItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            Product product = productRepository.findById(productId)
                    .orElseThrow(() -> new RuntimeException("Product not found"));
            CartItem item = new CartItem().user(user).product(product).quantity(quantity);
            cartItemRepository.save(item);
        }
    }

    public double getTotal(UUID userId) {
        List<CartItem> cartItems = getCartItems(userId);
        double total = cartItems.stream().mapToDouble(item -> item.getProduct().getPrice() * item.getQuantity()).sum();
        return total;
    }
}
