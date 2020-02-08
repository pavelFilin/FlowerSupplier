package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Cart {
    private long userId;

    List<CartItem> list = new ArrayList<>();
}
