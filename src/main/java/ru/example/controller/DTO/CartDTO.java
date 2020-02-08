package ru.filin.HavachMVC.controller.DTO;

import lombok.Data;
import ru.filin.HavachMVC.model.productManagement.entities.Product;

@Data
public class CartDTO {
    private long id;
    private Product product;
    private int quantity;
}
