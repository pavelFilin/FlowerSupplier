package ru.example.constants;

public enum OrderStatus {
    PROCESSING("Обработка"),
    PROCESSED("Обработано"),
    DELIVER("Доставляется"),
    DELIVERED("Доставлено"),
    FINISHED("Завершено"),
    CANCELED("Отменен");

    private String name;

    OrderStatus(String name) {
        this.name = name;
    }

    public static OrderStatus getByName(String orderStatus) {
        for (OrderStatus value : OrderStatus.values()) {
            if (value.name.equals(orderStatus)) {
                return value;
            }
        }
        return null;
    }

    @Override
    public String toString() {
        return name;
    }
}
