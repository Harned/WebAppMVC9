package com.WebApp.WebAppMVC9.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Product {
    private int id;

    @NotEmpty(message = "Title should not be empty")
    @Size(min = 2, max = 50, message = "Title should be between 2 and 50 characters")
    private String title;

    @Min(value = 0, message = "Price should be greater than 0")
    private int price;

    public Product() {

    }

    public Product(int id, String title, int price) {
        this.id = id;
        this.title = title;
        this.price = price;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}