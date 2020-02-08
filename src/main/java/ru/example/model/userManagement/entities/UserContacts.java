package ru.filin.HavachMVC.model.userManagement.entities;

import lombok.Data;

@Data
public class UserContacts {
    long id;
    long userId;
    String phone;
    String address;

    public UserContacts() {
    }

    public UserContacts(long userId, String phone, String address) {
        this.userId = userId;
        this.phone = phone;
        this.address = address;
    }

    public UserContacts(long id, long userId, String phone, String address) {
        this.id = id;
        this.userId = userId;
        this.phone = phone;
        this.address = address;
    }
}
