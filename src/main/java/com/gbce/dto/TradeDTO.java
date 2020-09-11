package com.gbce.dto;

import com.gbce.enums.TradeType;

import java.time.ZonedDateTime;

public class TradeDTO {
    private int quantity;
    private double price;
    private TradeType type;
    private ZonedDateTime timestamp;

    public TradeType getType() {
        return type;
    }

    public void setType(TradeType type) {
        this.type = type;
    }

    public ZonedDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(ZonedDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        if (quantity <= 0) {
            throw new IllegalArgumentException("Quantity should be more than zero");
        }
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) throws IllegalArgumentException {
        if (price < 0) {
            throw new IllegalArgumentException("Trade price can not be negative");
        }
        this.price = price;
    }
}
