package com.example.demo.entities;

import jakarta.persistence.*;
import java.util.Date;
import java.util.UUID;

import org.hibernate.annotations.GenericGenerator;

@SuppressWarnings("deprecation")
@Entity
@Table(name = "products")
public class Product {
    @Id
    @GeneratedValue(generator = "UUID")
    @GenericGenerator(name = "UUID", strategy = "org.hibernate.id.UUIDGenerator")
    @Column(name = "id", updatable = false, nullable = false)
    private UUID id;

    private String title;
    private double price;
    private String description;

    @Column(name = "image_url")
    private String imageUrl;

    private double rating;

    @Column(name = "num_reviews")
    private int numReviews;

    @Column(name = "date_created")
    private Date dateCreated;

    private String category;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false)
    private User userCreated;

    public UUID getId() {
        return this.id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public double getPrice() {
        return this.price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getImageUrl() {
        return this.imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    public double getRating() {
        return this.rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public int getNumReviews() {
        return this.numReviews;
    }

    public void setNumReviews(int numReviews) {
        this.numReviews = numReviews;
    }

    public Date getDateCreated() {
        return this.dateCreated;
    }

    public void setDateCreated(Date dateCreated) {
        this.dateCreated = dateCreated;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public User getUserCreated() {
        return this.userCreated;
    }

    public Product id(UUID id) {
        setId(id);
        return this;
    }

    public Product title(String title) {
        setTitle(title);
        return this;
    }

    public Product price(double price) {
        setPrice(price);
        return this;
    }

    public Product description(String description) {
        setDescription(description);
        return this;
    }

    public Product imageUrl(String imageUrl) {
        setImageUrl(imageUrl);
        return this;
    }

    public Product rating(double rating) {
        setRating(rating);
        return this;
    }

    public Product numReviews(int numReviews) {
        setNumReviews(numReviews);
        return this;
    }

    public Product dateCreated(Date dateCreated) {
        setDateCreated(dateCreated);
        return this;
    }

    public Product category(String category) {
        setCategory(category);
        return this;
    }

    public Product userCreated(User userCreated) {
        setUserCreated(userCreated);
        return this;
    }

    public void setUserCreated(User userCreated) {
        this.userCreated = userCreated;
    }

}