package ru.example.service.orderManagement.impl;

import org.springframework.stereotype.Service;
import ru.example.constants.OrderStatus;
import ru.example.constants.PaymentStatus;
import ru.example.constants.PaymentType;
import ru.example.controller.DTO.Message;
import ru.example.controller.DTO.OrderDTO;
import ru.example.controller.DTO.OrderItemDTO;
import ru.example.model.orderManagement.entities.CartItem;
import ru.example.model.orderManagement.entities.Order;
import ru.example.model.orderManagement.entities.OrderItemFull;
import ru.example.model.orderManagement.repositories.OrderRepository;
import ru.example.model.productManagement.entities.Product;
import ru.example.service.productManagement.product.ProductService;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class OrderServiceImpl {

    private CartServiceImpl cartService;

    private OrderRepository orderRepository;

    private ProductService productService;

    public OrderServiceImpl(CartServiceImpl cartService, OrderRepository orderRepository, ProductService productService) {
        this.cartService = cartService;
        this.orderRepository = orderRepository;
        this.productService = productService;
    }

    public long makeOrder(long userId,
                          String phone,
                          String address,
                          String paymentType,
                          Message message
    ) {
        List<CartItem> cartItems = cartService.findCartsByUser(userId);
        Map<CartItem, Product> cartMap = new HashMap<>();
        for (CartItem cartItem : cartItems) {
            cartMap.put(cartItem, productService.getById(cartItem.getProductId()));
        }

        int count = 0;
        int finalPrice = 0;

        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            count += entry.getKey().getQuantity();
            finalPrice += entry.getKey().getQuantity() * entry.getValue().getPrice();
        }

        Order order = new Order(userId,
                new Date(),
                PaymentType.valueOf(paymentType),
                count,
                phone,
                address,
                finalPrice,
                OrderStatus.PROCESSING,
                PaymentStatus.PENDING
        );

        validateQuantity(cartMap, message);
        if (message.getString().length() > 0) {
            return -1;
        }

        long orderId = saveOrderAndOrderItems(order, cartMap);
        cartService.purgeCartByUser(userId);
        return orderId;
    }

    private void validateQuantity(Map<CartItem, Product> cartMap, Message massage) {
        for (Map.Entry<CartItem, Product> entry : cartMap.entrySet()) {
            int quantity = entry.getKey().getQuantity();
            int stock = entry.getValue().getStock();
            if (stock < quantity) {
                massage.setString(massage.getString() + entry.getValue().getName() + " is not available (" + entry.getValue().getStock() + ")");
            }
        }
    }

    private long saveOrderAndOrderItems(Order order, Map<CartItem, Product> cartMap) {
        return orderRepository.saveOrderAndOrderItems(order, cartMap);
    }


    public OrderDTO getOrderByUserIdAndOrderId(long userId, long orderId) {
        Order order = orderRepository.findOrderByIdAndUserID(orderId, userId);
        List<OrderItemFull> orderItems = orderRepository.findOrderItemsByOrderId(orderId);
        List<OrderItemDTO> orderItemDTOS = orderItems.stream()
                .map(oi -> new OrderItemDTO(oi, productService.getById(oi.getProductId())))
                .collect(Collectors.toList());
        return new OrderDTO(order, orderItemDTOS);
    }

    public List<Order> getOrderByUser(long id) {
        List<Order> orders = orderRepository.getAll();
        return orders.stream()
                .filter(order -> order.getUserId() == id)
                .collect(Collectors.toList());
    }

    public List<Order> getOrders() {
        return orderRepository.getAll();
    }


    public void changeOrderStatus(long id, String orderStatus) {
        orderRepository.changeOrderStatus(id, OrderStatus.valueOf(orderStatus));
    }
}
