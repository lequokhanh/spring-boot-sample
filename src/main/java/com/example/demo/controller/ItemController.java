package com.example.demo.controller;

import java.util.List;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dtos.UploadItemDto;
import com.example.demo.entities.Product;
import com.example.demo.entities.User;
import com.example.demo.services.FileStorageService;
import com.example.demo.services.ItemService;

import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/item")
@RestController
public class ItemController {

    private final ItemService itemService;
    private final FileStorageService fileStorageService;

    public ItemController(ItemService itemService, FileStorageService fileStorageService) {
        this.itemService = itemService;
        this.fileStorageService = fileStorageService;
    }

    @GetMapping("/")
    public ResponseEntity<List<Product>> getItems(@RequestParam(required = false) String name) {
        if (name != null && !name.isEmpty()) {
            return ResponseEntity.ok(itemService.getItemsByName(name));
        } else {
            return ResponseEntity.ok(itemService.getItems());
        }
    }

    @GetMapping("/get-category")
    public ResponseEntity<List<String>> getMethodName() {
        return ResponseEntity.ok(itemService.getCategories());
    }

    @GetMapping("/catalogue/{category}")
    public ResponseEntity<List<Product>> getItemsByCategory(@PathVariable String category) {
        return ResponseEntity.ok(itemService.getItemsByCategory(category));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getItemDetail(@PathVariable String id) {
        return ResponseEntity.ok(itemService.getItemDetail(id));
    }

    @PostMapping(value = "/", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<String> uploadItem(@RequestParam(name = "file") MultipartFile file,
            @ModelAttribute UploadItemDto body) {
        String fileName = fileStorageService.storeFile(file);
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();

        User currentUser = (User) authentication.getPrincipal();

        itemService.uploadItem(body, fileName, currentUser);
        return ResponseEntity.ok("Item uploaded successfully");
    }

}
