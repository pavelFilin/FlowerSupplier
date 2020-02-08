package ru.filin.HavachMVC.controller.DTO;

import lombok.Data;
import ru.filin.HavachMVC.model.orderManagement.entities.OrderItemFull;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

@Data
public class OrderItemDTO {

    private long id;

    private Product product;

    private long orderId;

    private int price;

    private int quantity;

    private int totalPrice;

    public OrderItemDTO() {
    }

    public OrderItemDTO(Product product, long orderId, int price, int quantity, int totalPrice) {
        this.product = product;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderItemDTO(long id, Product product, long orderId, int price, int quantity, int totalPrice) {
        this.id = id;
        this.product = product;
        this.orderId = orderId;
        this.price = price;
        this.quantity = quantity;
        this.totalPrice = totalPrice;
    }

    public OrderItemDTO(OrderItemFull orderItem, Product product) {
        this(orderItem.getId(), product, orderItem.getOrderId(), orderItem.getPrice(), orderItem.getQuantity(), orderItem.getTotalPrice());
        this.product = product;
    }
}
