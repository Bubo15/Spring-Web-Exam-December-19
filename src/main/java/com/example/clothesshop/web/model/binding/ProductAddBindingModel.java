package com.example.clothesshop.web.model.binding;

import com.example.clothesshop.data.entities.Category;
import com.example.clothesshop.data.entities.Sex;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

public class ProductAddBindingModel {

    private long id;
    private String name;
    private String description;
    private double price;
    private Category category;
    private Sex sex;

    @Length(min = 1, message = "Name must be least 1 s")
    public String getName() {
        return name;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    @NotNull(message = "It required")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Min(value = 0, message = "Price can't be negative")
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @NotNull(message = "It required")
    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @NotNull(message = "It required")
    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }
}
