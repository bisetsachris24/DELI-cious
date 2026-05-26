package com.pluralsight.models;

public class Drink {
    // Enum used to define available drink sizes.
    public enum Size {
        SMALL, MEDIUM, LARGE
    }

    private Size size;
    private String flavor;

    // Constructor used to create a drink with a size and flavor.
    public Drink(Size size, String flavor) {
        this.size = size;
        this.flavor = flavor;
    }

    public double getPrice() {
        return switch (size) {
            case SMALL -> 2.00;
            case MEDIUM -> 2.50;
            case LARGE -> 3.00;
        };
    }

    // Returns a formatted summary string for receipts or order displays.
    public String getSummary() {
        return String.format("  Drink: %s %s  -  $%.2f%n",
                size.name().charAt(0) + size.name().substring(1).toLowerCase(),
                flavor, getPrice());
    }

    //  Getters

    public Size getSize() {
        return size;
    }

    public String getFlavor() {
        return flavor;
    }

}
