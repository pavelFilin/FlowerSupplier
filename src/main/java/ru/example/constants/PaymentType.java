package ru.example.constants;

public enum PaymentType {
    CASH("Наличные"),
    CARD("Карта"),
    CARD_ON_SITE("Картой на месте");

    private String name;

    PaymentType(String name) {
        this.name = name;
    }


    @Override
    public String toString() {
        return name;
    }
}
