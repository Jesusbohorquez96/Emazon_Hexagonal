package com.jbohorquez.emazon_hexagonal.domain.model;

import java.util.Set;

public class Article {

    private Long id;
    private String name;
    private String description;
    private Integer stock;
    private Double price;
    private Brand brand;
    private Set<Category> categories;

    public Article(Long id, String name, String description, Integer stock, Double price, Brand brand, Set<Category> categories) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.stock = stock;
        this.price = price;
        this.brand = brand;
        this.categories = categories;
    }

    public Article() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public Brand getBrand() {
        return brand;
    }

    public void setBrand(Brand brand) {
        this.brand = brand;
    }

    public Set<Category> getCategories() {
        return categories;
    }

    public void setCategories(Set<Category> categories) {
        this.categories = categories;
    }

}
