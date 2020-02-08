package ru.filin.HavachMVC.model.orderManagement.entities;

import lombok.Data;
import ru.filin.HavachMVC.constants.OrderStatus;
import ru.filin.HavachMVC.constants.PaymentStatus;
import ru.filin.HavachMVC.constants.PaymentType;

import java.util.Date;

@Data
public class Order {
    private long id;

    private long userId;

    private Date timeCreated;

    private PaymentType paymentType;

    private String phone;

    private String address;

    private int quantity;

    private int finalPrice;

    private OrderStatus orderStatus;

    private PaymentStatus paymentStatus;

    public Order() {
    }

    public Order(long id, long userId, Date timeCreated, PaymentType paymentType, int quantity, String phone, String address, int finalPrice, OrderStatus orderStatus, PaymentStatus paymentStatus) {
        this.id = id;
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.phone = phone;
        this.address = address;
        this.quantity = quantity;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }

    public Order(long userId, Date timeCreated, PaymentType paymentType, int quantity, String phone, String address,  int finalPrice, OrderStatus orderStatus, PaymentStatus paymentStatus ) {
        this.userId = userId;
        this.timeCreated = timeCreated;
        this.paymentType = paymentType;
        this.quantity = quantity;
        this.phone = phone;
        this.address = address;
        this.finalPrice = finalPrice;
        this.orderStatus = orderStatus;
        this.paymentStatus = paymentStatus;
    }


}
