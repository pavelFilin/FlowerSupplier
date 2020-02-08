package ru.example.model.orderManagement.entities;

import lombok.Data;
import ru.example.model.productManagement.entities.Product;

@Data
public class OrderItem {
    private Product product;

    private int quantity;

    private int totalPrice;
}
