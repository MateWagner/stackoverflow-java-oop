package com.codecool.stackoverflowtw.queston.data;

import java.util.Arrays;
import java.util.Optional;

public enum ColumnNameForOrder {
    ANSWER_COUNT,
    DATE,
    TITLE;

    public static Optional<ColumnNameForOrder> isMatchAny(String str) {
        return Arrays.stream(ColumnNameForOrder.values())
                .filter(requestColumnNames -> requestColumnNames.name().equals(str.toUpperCase()))
                .findFirst();
    }
}
