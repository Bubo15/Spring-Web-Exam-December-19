package com.example.clothesshop.data.entities;

import javax.persistence.*;

@Entity
@Table(name = "products")
public class Product extends BaseEntity{

    private String name;
    private String description;
    private double price;
    private Category category;
    private Sex sex;

    public Product() {
    }

    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Column(name = "description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Column(name = "price")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Column(name = "category")
    @Enumerated(EnumType.STRING)
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Column(name = "sex")
    @Enumerated(EnumType.STRING)
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
