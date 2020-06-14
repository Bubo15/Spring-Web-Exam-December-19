package com.example.clothesshop.service.model;

import com.example.clothesshop.data.entities.Category;
import com.example.clothesshop.data.entities.Sex;
import com.example.clothesshop.data.entities.User;

public class ProductServiceModel {

    private long id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private Sex sex;
    private User user;

    public ProductServiceModel() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
