package ru.example.controller.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ru.example.model.orderManagement.entities.CartItem;
import ru.example.service.productManagement.product.ProductService;

@Component
public class CartConvector {


   static private ProductService productService;

    @Autowired
    public CartConvector(ProductService productService) {
        this.productService = productService;
    }

    public static CartDTO toCartDTO(CartItem cart) {
        CartDTO cartDTO = new CartDTO();
        cartDTO.setId(cart.getId());
        cartDTO.setProduct(productService.getById(cart.getProductId()));
        cartDTO.setQuantity(cart.getQuantity());
        return cartDTO;
    }
}
