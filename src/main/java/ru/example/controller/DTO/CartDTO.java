package ru.example.controller.DTO;

import lombok.Data;
import ru.example.model.productManagement.entities.Product;

@Data
public class CartDTO {
    private long id;
    private Product product;
    private int quantity;
}
