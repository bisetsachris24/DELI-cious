package com.pluralsight.models;

public class Topping {
    public enum ToppingType {
        MEAT, CHEESE, REGULAR, SAUCE, SIDE
    }

    private String name;
    private ToppingType type;
    private boolean isExtra;

    public Topping(String name, ToppingType type) {
        this.name = name;
        this.type = type;
        this.isExtra = false;
    }

    public Topping(String name, ToppingType type, boolean isExtra) {
        this.name = name;
        this.type = type;
        this.isExtra = isExtra;
    }

    public double getPrice(int sandwichSize) {
        switch (type) {
            case MEAT:
                if (isExtra) {
                    return switch (sandwichSize) {
                        case 4 -> 0.50;
                        case 8 -> 1.00;
                        case 12 -> 1.50;
                        default -> 0.0;
                    };
                } else {
                    return switch (sandwichSize) {
                        case 4 -> 1.00;
                        case 8 -> 2.00;
                        case 12 -> 3.00;
                        default -> 0.0;
                    };
                }

            case CHEESE:
                if (isExtra) {
                    return switch (sandwichSize) {
                        case 4 -> 0.30;
                        case 8 -> 0.60;
                        case 12 -> 0.90;
                        default -> 0.0;
                    };
                } else {
                    return switch (sandwichSize) {
                        case 4 -> 0.75;
                        case 8 -> 1.50;
                        case 12 -> 2.25;
                        default -> 0.0;
                    };
                }
            default:
        }

        return 0.0;
    }
    // Getters

    public String getName() { return name; }
    public ToppingType getType() { return type; }
    public boolean isExtra() { return isExtra; }
    public void setExtra(boolean extra) { isExtra = extra; }

    @Override
    public String toString() {
        String label = name;
        if (isExtra) label += " (extra)";
        return label;
    }

}
