package ru.filin.HavachMVC.model.orderManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;

import java.util.List;

@Repository
public class CartRepositoryImpl {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public CartRepositoryImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public List<CartItem> findCartsByUser(long userId) {
        String getCartQuery = "SELECT * FROM cart_item WHERE user_id = ?";
        return jdbcTemplate.query(
                getCartQuery,
                new Object[]{userId},
                (rs, rowNum) -> new CartItem(
                        rs.getLong("id"),
                        rs.getLong("user_id"),
                        rs.getLong("product_id"),
                        rs.getInt("quantity")
                ));
    }

    public CartItem findCartByUserAndProduct(long userId, long productId) {
        String getCartQuery = "SELECT * FROM cart_item WHERE user_id = ? AND product_id = ?";
        CartItem cartItems = jdbcTemplate.queryForObject(getCartQuery, new Object[]{userId, productId}, (rs, rowNum) -> new CartItem(rs.getLong("user_id"), rs.getLong("product_id"), rs.getInt("quantity")));

        return cartItems;
    }

    public void saveCartItem(CartItem cart) {
        CartItem cartFromDb = null;
        try {
            cartFromDb = findCartByUserAndProduct(cart.getUserId(), cart.getProductId());
        } catch (EmptyResultDataAccessException e) {
        }

        if (cartFromDb == null) {
            insertCartItem(cart);
        } else {
            cart.setId(cartFromDb.getId());
            updateCartItem(cart);
        }
    }

    private void insertCartItem(CartItem cart) {
        String insertCartItem = "INSERT INTO  cart_item (user_id, product_id, quantity) VALUES (?, ?, ?)";
        jdbcTemplate.update(insertCartItem, cart.getUserId(), cart.getProductId(), cart.getQuantity());
    }

    private void updateCartItem(CartItem cart) {
        String insertCartItem = "UPDATE cart_item SET user_id = ?, product_id = ?, quantity = ? WHERE id = ?";
        jdbcTemplate.update(insertCartItem, cart.getUserId(), cart.getProductId(), cart.getQuantity(), cart.getId());
    }

    public void purgeCartByUser(long userId) {
        String purge = "DELETE from cart_item WHERE user_id = ?";
        jdbcTemplate.update(purge, userId);
    }

    public void changeQuantity(long userId, long productId, int quantity) {
        String changeQuantity = "UPDATE cart_item SET quantity = ? WHERE user_id = ? AND  product_id = ?";
        jdbcTemplate.update(changeQuantity, quantity, userId, productId);
    }

    public void deleteCartItem(long cartId) {
        String deleteCartItem = "DELETE from cart_item WHERE id = ?";
        jdbcTemplate.update(deleteCartItem, cartId);
    }
}
