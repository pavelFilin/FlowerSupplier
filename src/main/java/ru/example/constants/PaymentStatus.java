package ru.example.constants;

public enum PaymentStatus {
    PENDING("Запрос на оплату"),
    WAITING("Ожидание"),
    PAID("Оплачено");

    private String name;

    PaymentStatus(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
