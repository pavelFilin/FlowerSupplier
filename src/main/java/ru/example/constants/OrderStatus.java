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

    @Override
    public String toString() {
        return name;
    }
}
