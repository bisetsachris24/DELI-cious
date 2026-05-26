package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

// Handles sandwich details, toppings, pricing, and receipt summaries.

public class Sandwich {

    // Stores the selected bread type (white, wheat, rye, wrap, etc.)
    private String breadType;

    // Stores the sandwich size in inches (4, 8, or 12).
    private int size;

    // Indicates whether the sandwich should be toasted.
    private boolean toasted;

    // Lists used to organize toppings by category.
    private List<Topping> meats;
    private List<Topping> cheeses;
    private List<Topping> regularToppings;
    private List<Topping> sauces;
    private List<Topping> sides;

    // Base sandwich price before toppings are added.
    private double basePrice;

     // Constructor used to create a sandwich with bread type,

    public Sandwich(String breadType, int size, boolean toasted) {
        this.breadType = breadType;
        this.size = size;
        this.toasted = toasted;

        // Initialize topping lists.
        this.meats = new ArrayList<>();
        this.cheeses = new ArrayList<>();
        this.regularToppings = new ArrayList<>();
        this.sauces = new ArrayList<>();
        this.sides = new ArrayList<>();

        // Determine base sandwich price based on size.
        this.basePrice = switch (size) {
            case 4  -> 5.50;
            case 8  -> 7.00;
            case 12 -> 8.50;

            // Throw exception if an invalid sandwich size is entered.
            default -> throw new IllegalArgumentException(
                    "Invalid size. Must be 4, 8, or 12.");
        };
    }

    //  Methods for adding toppings

    // Adds a meat topping and allows extra meat selection.
    public void addMeat(String meatName, boolean isExtra) {
        meats.add(new Topping(meatName,
                Topping.ToppingType.MEAT, isExtra));
    }

    // Adds a cheese topping and allows extra cheese selection.
    public void addCheese(String cheeseName, boolean isExtra) {
        cheeses.add(new Topping(cheeseName,
                Topping.ToppingType.CHEESE, isExtra));
    }

    // Adds a regular topping (lettuce, tomatoes, onions, etc.).
    public void addTopping(String toppingName) {
        regularToppings.add(new Topping(
                toppingName,
                Topping.ToppingType.REGULAR));
    }

    // Adds a sauce topping.
    public void addSauce(String sauceName) {
        sauces.add(new Topping(
                sauceName,
                Topping.ToppingType.SAUCE));
    }

    // Adds a side item to the sandwich.
    public void addSide(String sideName) {
        sides.add(new Topping(
                sideName,
                Topping.ToppingType.SIDE));
    }

    // Price calculation
    // Calculates the total sandwich price by adding topping prices to the base sandwich price.

    public double getPrice() {

        // Start with the sandwich base price.
        double total = basePrice;

        // Add meat prices.
        for (Topping t : meats)
            total += t.getPrice(size);

        // Add cheese prices.
        for (Topping t : cheeses)
            total += t.getPrice(size);

        // Regular toppings, sauces, and sides are included for free.

        return total;
    }

    //  Receipt / Order Summary

    //Builds and returns a formatted sandwich summary for receipts or order confirmation.

    public String getSummary() {

        StringBuilder sb = new StringBuilder();

        // Add sandwich size and bread type.
        sb.append(String.format(
                "  %d\" %s sandwich",
                size,
                breadType));

        // Display toasted label if selected.
        if (toasted)
            sb.append(" [TOASTED]");

        sb.append("\n");

        // Display meat toppings.
        if (!meats.isEmpty()) {
            sb.append("    Meats: ");

            meats.forEach(m -> sb.append(m).append(", "));

            // Remove trailing comma and space.
            sb.setLength(sb.length() - 2);

            sb.append("\n");
        }

        // Display cheese toppings.
        if (!cheeses.isEmpty()) {
            sb.append("    Cheeses: ");

            cheeses.forEach(c -> sb.append(c).append(", "));

            sb.setLength(sb.length() - 2);

            sb.append("\n");
        }

        // Display regular toppings.
        if (!regularToppings.isEmpty()) {
            sb.append("    Toppings: ");

            regularToppings.forEach(
                    t -> sb.append(t.getName()).append(", "));

            sb.setLength(sb.length() - 2);

            sb.append("\n");
        }

        // Display sauces.
        if (!sauces.isEmpty()) {
            sb.append("    Sauces: ");

            sauces.forEach(
                    s -> sb.append(s.getName()).append(", "));

            sb.setLength(sb.length() - 2);

            sb.append("\n");
        }

        // Display side toppings/items.
        if (!sides.isEmpty()) {
            sb.append("    Sides: ");

            sides.forEach(
                    s -> sb.append(s.getName()).append(", "));

            sb.setLength(sb.length() - 2);

            sb.append("\n");
        }

        // Display final sandwich price.
        sb.append(String.format(
                "    Price: $%.2f%n",
                getPrice()));

        return sb.toString();
    }

    //  Getters

    // Returns sandwich bread type.
    public String getBreadType() {
        return breadType;
    }

    // Returns sandwich size.
    public int getSize() {
        return size;
    }

    // Returns toasted status.
    public boolean isToasted() {
        return toasted;
    }

    // Returns list of meats.
    public List<Topping> getMeats() {
        return meats;
    }

    // Returns list of cheeses.
    public List<Topping> getCheeses() {
        return cheeses;
    }

    // Returns list of regular toppings.
    public List<Topping> getRegularToppings() {
        return regularToppings;
    }

    // Returns list of sauces.
    public List<Topping> getSauces() {
        return sauces;
    }

    // Returns list of side items.
    public List<Topping> getSides() {
        return sides;
    }
}