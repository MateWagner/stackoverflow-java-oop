package com.codecool.stackoverflowtw.queston.data;

import java.util.Arrays;
import java.util.Optional;

public enum Order {
    ASC,
    DESC;
    public static Optional<Order> isMatchAny(String str) {
        return Arrays.stream(Order.values())
                .filter(order -> order.name().equals(str.toUpperCase()))
                .findFirst();
    }
}
