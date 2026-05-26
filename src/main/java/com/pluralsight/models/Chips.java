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
    public String getSummary() {
        return String.format("  Chips: %s  -  $%.2f%n", chipType, getPrice());
    }

    // Getter

    public String getChipType() { return chipType; }
}
