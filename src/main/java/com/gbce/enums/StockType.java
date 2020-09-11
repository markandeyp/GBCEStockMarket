package com.gbce.enums;

public enum StockType {
    Common("Common"),
    Preferred("Preferred");

    private String type;

    private StockType(String type) {
        this.type = type;
    }
}
