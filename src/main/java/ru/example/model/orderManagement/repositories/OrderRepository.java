package ru.example.model.orderManagement.repositories;

import org.springframework.jdbc.core.RowMapper;
import ru.example.constants.OrderStatus;
import ru.example.constants.PaymentType;
import ru.example.model.productManagement.entities.Product;
import ru.example.constants.PaymentStatus;
import ru.example.model.BaseRepository;
import ru.example.model.orderManagement.entities.CartItem;
import ru.example.model.orderManagement.entities.Order;
import ru.example.model.orderManagement.entities.OrderItemFull;

import java.sql.ResultSet;
import java.util.Date;
import java.util.List;
import java.util.Map;

public interface OrderRepository extends BaseRepository<Order> {
    RowMapper<Order> OderMapper = (ResultSet resultSet, int rowNum) -> {
        return new Order(
                resultSet.getLong("id"),
                resultSet.getLong("user_id"),
                new Date(resultSet.getTimestamp("time_created").getTime()),
                PaymentType.valueOf(resultSet.getString("payment_type")),
                resultSet.getInt("quantity"),
                resultSet.getString("phone"),
                resultSet.getString("address"),
                resultSet.getInt("finalprice"),
                OrderStatus.valueOf(resultSet.getString("orderstatus")),
                PaymentStatus.valueOf(resultSet.getString("payment_status"))
        );
    };

    RowMapper<OrderItemFull> OderItemMapper = (ResultSet resultSet, int rowNum) -> {
        return new OrderItemFull(
                resultSet.getLong("id"),
                resultSet.getLong("product_id"),
                resultSet.getLong("order_id"),
                resultSet.getInt("price"),
                resultSet.getInt("quantity"),
                resultSet.getInt("total_price")
        );
    };


    long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap);

    Order findOrderByIdAndUserID(long orderId, long userId);

    List<OrderItemFull> findOrderItemsByOrderId(long orderId);

    void changeOrderStatus(long id, OrderStatus orderStatus);
}
