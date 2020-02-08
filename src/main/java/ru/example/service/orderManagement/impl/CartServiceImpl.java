package ru.filin.HavachMVC.service.orderManagement.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.orderManagement.repositories.impl.CartRepositoryImpl;

import java.util.List;

@Service
public class CartServiceImpl {

    private CartRepositoryImpl cartRepository;

    @Autowired
    public CartServiceImpl(CartRepositoryImpl cartRepository) {
        this.cartRepository = cartRepository;
    }

    public List<CartItem> findCartsByUser(long userId) {
        return cartRepository.findCartsByUser(userId);
    }

    public CartItem findCartByUserAndProduct(long userId, long productId) {
        return cartRepository.findCartByUserAndProduct(userId, productId);
    }

    public void saveCartItem(CartItem cart) {
        cartRepository.saveCartItem(cart);
    }

    public void saveCartItem(long userId, long productId, int quantity) {
        CartItem cartItem = new CartItem(userId, productId, quantity);
        saveCartItem(cartItem);
    }

    public void purgeCartByUser(long userId) {
        cartRepository.purgeCartByUser(userId);
    }

    public void changeQuantity(long userId, long productId, int quantity) {
        cartRepository.changeQuantity(userId, productId, quantity);
    }

    public void deleteCartItem(long cartId) {
        cartRepository.deleteCartItem(cartId);
    }
}
