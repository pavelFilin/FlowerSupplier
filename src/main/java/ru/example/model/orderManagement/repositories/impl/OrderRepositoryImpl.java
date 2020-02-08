package ru.filin.HavachMVC.model.orderManagement.repositories.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.model.orderManagement.entities.CartItem;
import ru.filin.HavachMVC.model.orderManagement.entities.Order;
import ru.filin.HavachMVC.model.orderManagement.entities.OrderItemFull;
import ru.filin.HavachMVC.model.orderManagement.repositories.OrderRepository;
import ru.filin.HavachMVC.model.productManagement.entities.Product;
import ru.filin.HavachMVC.model.productManagement.repositories.product.ProductRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public class OrderRepositoryImpl implements OrderRepository {

    private ProductRepository productRepository;

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public OrderRepositoryImpl(ProductRepository productRepository, JdbcTemplate jdbcTemplate) {
        this.productRepository = productRepository;
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Order> getAll() {
        String query = "SELECT * FROM order_table";
        return jdbcTemplate.query(query, OderMapper);
    }

    @Override
    public Order getById(long id) {
        String query = "SELECT * FROM order_table WHERE id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{id}, OderMapper);
    }

    @Override
    public void delete(long id) {
        String query = "DELETE FROM order_table WHERE id = ?";
        jdbcTemplate.update(query, new Object[]{id});
    }

    @Override
    public void update(Order obj) {
        String query = "UPDATE order_table SET user_id = ?, time_created = ?, payment_type = ?, phone = ?, finalprice = ?, orderstatus = ?, payment_status = ? WHERE id = ?";
        jdbcTemplate.update(query,
                obj.getUserId(),
                obj.getTimeCreated(),
                obj.getPaymentType(),
                obj.getPhone(),
                obj.getAddress(),
                obj.getFinalPrice(),
                obj.getOrderStatus(),
                obj.getPaymentStatus(),
                obj.getId());
    }

    @Override
    public Long save(Order obj) {

        String getOrderId = "SELECT nextval(pg_get_serial_sequence('cart', 'id'))";

        long orderId = jdbcTemplate.query(getOrderId, rs -> {
            rs.next();
            return rs.getLong("nextval");
        });

        String query = "INSERT INTO order_table (id, user_id, time_created, payment_type, phone, address, quantity, finalprice, orderstatus, payment_status) VALUES (?,?,?,?,?,?,?,?,?,?)";
        jdbcTemplate.update(query,
                orderId,
                obj.getUserId(),
                obj.getTimeCreated(),
                obj.getPaymentType().name(),
                obj.getPhone(),
                obj.getAddress(),
                obj.getQuantity(),
                obj.getFinalPrice(),
                obj.getOrderStatus().name(),
                obj.getPaymentStatus().name()
        );
        return orderId;
    }

    @Override
    @Transactional
    public long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap) {
        Long orderId = save(order);
        saveCarts(cartMap, orderId);
        Map<Long, Integer> newQuantity = new HashMap<>();
        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            int quantity = entry.getKey().getQuantity();
            int stock = entry.getValue().getStock();
            productRepository.updateStock(entry.getValue().getId(), (stock-quantity));
        }
        return orderId;
    }

    @Override
    public Order findOrderByIdAndUserID(long orderId, long userId) {
        String query = "SELECT * FROM order_table WHERE id = ? AND user_id = ?";
        return jdbcTemplate.queryForObject(query, new Object[]{orderId, userId}, OderMapper);
    }

    @Override
    public List<OrderItemFull> findOrderItemsByOrderId(long orderId) {
        String query = "SELECT * FROM order_item WHERE order_id = ?";
        return jdbcTemplate.query(query, new Object[]{orderId}, OderItemMapper);
    }

    @Override
    public void changeOrderStatus(long id, OrderStatus orderStatus) {
        String query = "UPDATE order_table SET orderstatus = ? WHERE id = ?";
        jdbcTemplate.update(query, orderStatus.name(), id);
    }

    private void saveCarts(Map<CartItem, Product> cartMap, Long orderId) {
        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            saveOrderItem(
                    entry.getValue().getId(),
                    orderId,
                    entry.getKey().getQuantity(),
                    entry.getValue().getPrice(),
                    entry.getKey().getQuantity() * entry.getValue().getPrice());
        }
    }

    private void saveOrderItem(long productId, long orderId, int quantity, int price, int totalPrice) {
        String saveOrderItemQuery = "INSERT INTO order_item (order_id, product_id, quantity, price, total_price) VALUES (?,?,?,?,?)";
        jdbcTemplate.update(
                saveOrderItemQuery,
                orderId,
                productId,
                quantity,
                price,
                totalPrice);
    }
}
