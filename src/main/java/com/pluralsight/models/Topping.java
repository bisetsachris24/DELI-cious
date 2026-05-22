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

}
