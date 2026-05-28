package com.pluralsight.models;

public class PhillyCheeseSteak extends Sandwich {

    public static final String SIGNATURE_NAME = "Philly Cheese Steak";

    public PhillyCheeseSteak() {
        // 8-inch white bread, toasted
        super("white", 8, true);

        // Preload the Philly recipe
        addMeat("steak", false);
        addCheese("american", false);
        addTopping("peppers");
        addSauce("mayo");
    }
    //Returns the signature sandwich name. Useful for display in menus.

    public String getSignatureName() {
        return SIGNATURE_NAME;
    }

    @Override
    public String getSummary() {
        return "  [Signature: " + SIGNATURE_NAME + "]\n" + super.getSummary();
    }
}