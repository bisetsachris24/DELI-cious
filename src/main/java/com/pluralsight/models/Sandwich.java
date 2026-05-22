package com.pluralsight.models;

public class Sandwich {

    // I am creating prperties for sandwich that handles bread type, size and toppings
    // i
    private String breadType;
    private int size;
    private boolean toasted;

    // Constructor
    public Sandwich(String breadType, int size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;
    }



}
