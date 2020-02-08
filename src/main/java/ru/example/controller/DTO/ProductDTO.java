package ru.filin.HavachMVC.controller.DTO;

import lombok.Data;
import ru.filin.HavachMVC.model.productManagement.entities.Category;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

@Data
public class ProductDTO {
    private long id;

    private String name;

    private String description;

    private int price;

    private long detailsId;

    private int stock;

    private boolean active;

    private String photo;

    private long categoryId;

    private Category category;

    public ProductDTO(long id, String name, String description, int price, long detailsId, int stock, boolean active, String photo, long categoryId) {
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

    public ProductDTO(long id, String name, String description, int price, long detailsId, int stock, boolean active, String photo, long categoryId, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.price = price;
        this.detailsId = detailsId;
        this.stock = stock;
        this.active = active;
        this.photo = photo;
        this.categoryId = categoryId;
        this.category = category;
    }

    public ProductDTO(Product product) {
        this.id = product.getId();
        this.name = product.getName();
        this.description = product.getDescription();
        this.price = product.getPrice();
        this.detailsId = product.getDetailsId();
        this.stock = product.getStock();
        this.active = product.isActive();
        this.photo = product.getPhoto();
        this.categoryId = product.getCategoryId();
    }
}
