package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;

@Data
public class CartItem {
    private long id;

    private long userId;

    private long productId;

    private int quantity;

    public CartItem() {
    }

    public CartItem(long userId, long productId, int quantity) {
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }

    public CartItem(long id, long userId, long productId, int quantity) {
        this.id = id;
        this.userId = userId;
        this.productId = productId;
        this.quantity = quantity;
    }



}
