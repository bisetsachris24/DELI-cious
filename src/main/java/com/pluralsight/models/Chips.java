package com.pluralsight.models;

// Represents a bag of chips added to an order.

public class Chips {

    private static final double PRICE = 1.50;

    private String chipType;

    public Chips(String chipType) {
        this.chipType = chipType;
    }

    public double getPrice() {
        return PRICE;
    }
}
