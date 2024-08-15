package com.example.demo.services;

import java.util.List;
import java.util.UUID;

import org.springframework.stereotype.Service;

import com.example.demo.dtos.UploadItemDto;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.repositories.ProductRepository;

@Service
public class ItemService {

    private ProductRepository productRepository;

    public ItemService(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    public List<Product> getItems() {
        return (List<Product>) productRepository.findAll();
    }

    public List<Product> getItemsByName(String name) {
        return productRepository.findByTitle(name);
    }

    public List<String> getCategories() {
        return productRepository.findAll().stream().map(Product::getCategory).distinct().toList();
    }

    public List<Product> getItemsByCategory(String category) {
        return productRepository.findByCategory(category);
    }

    public Product getItemDetail(String id) {
        return productRepository.findById(UUID.fromString(id))
                .orElseThrow(() -> new RuntimeException("Product not found"));
    }

    public void uploadItem(UploadItemDto body, String fileName, User user) {
        Product product = new Product()
                .title(body.getTitle())
                .description(body.getDescription())
                .price(body.getPrice())
                .category(body.getCategory())
                .imageUrl("http://localhost:5000/api/v1/files/" + fileName)
                .userCreated(user);
        productRepository.save(product);
    }

}
