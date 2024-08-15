package com.example.demo.dtos;

public class UploadItemDto {
    private String title;
    private String description;
    private String category;
    private double price;

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public UploadItemDto title(String title) {
        setTitle(title);
        return this;
    }

    public UploadItemDto description(String description) {
        setDescription(description);
        return this;
    }

    public UploadItemDto category(String category) {
        setCategory(category);
        return this;
    }

    public UploadItemDto price(double price) {
        setPrice(price);
        return this;
    }

}
