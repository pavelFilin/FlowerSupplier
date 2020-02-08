package ru.filin.HavachMVC.model.productManagement.entities;

import lombok.Data;

@Data
public class Product {
    private long id;

    private String name;

    private String description;

    private int price;

    private long detailsId;

    private int stock;

    private boolean active;

    private String photo;

    private long categoryId;

    public Product() {
    }

    public Product(long id, String name, String description, int price, long detailsId, int stock, boolean active, String photo, long categoryId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.detailsId = detailsId;
        this.stock = stock;
        this.active = active;
        this.photo = photo;
        this.categoryId = categoryId;
    }
}
