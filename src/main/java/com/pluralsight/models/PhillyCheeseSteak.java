package com.pluralsight.models;

public class PhillyCheeseSteak extends Sandwich {

    public static final String SIGNATURE_NAME = "Philly Cheese Steak";

    public PhillyCheeseSteak() {
        // 8-inch white bread, toasted
        super("white", 8, true);

        // Pre-load the Philly recipe
        addMeat("steak", false);
        addCheese("american", false);
        addTopping("peppers");
        addSauce("mayo");
    }
}