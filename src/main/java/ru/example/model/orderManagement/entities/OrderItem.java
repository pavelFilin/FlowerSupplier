package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

@Data
public class OrderItem {
    private Product product;

    private int quantity;

    private int totalPrice;
}
