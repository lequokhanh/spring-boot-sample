package com.example.demo.repositories;

import com.example.demo.entities.Product;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductRepository extends JpaRepository<Product, UUID> {
    @Query("SELECT p FROM Product p WHERE p.title LIKE %:title%")
    List<Product> findByTitle(String title);

    List<Product> findByCategory(String category);

    @SuppressWarnings("null")
    Optional<Product> findById(UUID id);
}
