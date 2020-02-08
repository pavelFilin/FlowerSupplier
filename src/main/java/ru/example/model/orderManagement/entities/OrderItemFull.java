package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;

@Data
public class OrderItemFull {

    private long id;

    private long productId;

    private long orderId;

    private int price;

    private int quantity;

    private int totalPrice;

    public OrderItemFull(long id, long productId, long orderId, int price, int quantity, int totalPrice) {
        this.id = id;
        this.productId = productId;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }
}
