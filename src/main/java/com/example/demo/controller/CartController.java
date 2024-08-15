package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.AddCartItemBodyDto;
import com.example.demo.dtos.UpdateCartItemBodyDto;
import com.example.demo.entities.CartItem;
import com.example.demo.entities.User;
import com.example.demo.services.CartService;

import java.util.List;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequestMapping("/cart")
@RestController
public class CartController {

    private final CartService cartService;

    public CartController(CartService cartService) {
        this.cartService = cartService;
    }

    @GetMapping("/")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<List<CartItem>> getMethodName() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        List<CartItem> cartItems = cartService.getCartItems(currentUser.getId());

        return ResponseEntity.ok(cartItems);
    }

    @PostMapping("/update")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> updateItem(@RequestBody UpdateCartItemBodyDto body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        cartService.updateItem(body, currentUser.getId());

        return ResponseEntity.ok("Item updated successfully");
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<String> addItem(@RequestBody AddCartItemBodyDto body) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        cartService.addItem(body, currentUser);

        return ResponseEntity.ok("Item added successfully");
    }

    @GetMapping("/total")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<Double> getTotal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        double total = cartService.getTotal(currentUser.getId());

        return ResponseEntity.ok(total);
    }

}
