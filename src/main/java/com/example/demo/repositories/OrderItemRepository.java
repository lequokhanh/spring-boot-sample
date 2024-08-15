package com.example.demo.repositories;

import com.example.demo.entities.OrderItem;

import java.util.Optional;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderItemRepository extends CrudRepository<OrderItem, UUID> {
    Optional<OrderItem> findByUserId(UUID userId);
}
