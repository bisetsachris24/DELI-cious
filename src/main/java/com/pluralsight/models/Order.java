package com.pluralsight.models;

import java.util.ArrayList;
import java.util.List;

 // Represents a customer order containing sandwiches, drinks, and chips.

public class Order {

    private List<Sandwich> sandwiches;
    private List<Drink>    drinks;
    private List<Chips>    chipsList;

    public Order() {
        this.sandwiches = new ArrayList<>();
        this.drinks = new ArrayList<>();
        this.chipsList = new ArrayList<>();
    }
    // Add items

    public void addSandwich(Sandwich sandwich) { sandwiches.add(sandwich); }
    public void addDrink(Drink drink)          { drinks.add(drink); }
    public void addChips(Chips chips)          { chipsList.add(chips); }

    //  Pricing

    public double getTotal() {
        double total = 0.0;
        for (Sandwich s : sandwiches) total += s.getPrice();
        for (Drink d    : drinks)     total += d.getPrice();
        for (Chips c    : chipsList)  total += c.getPrice();
        return total;
    }

    //  Validation

     // An order is valid if it has at least one sandwich, or at least one drink or chips. (Zero-sandwich orders require chips or a drink.)

    public boolean isValid() {
        return !sandwiches.isEmpty() || !drinks.isEmpty() || !chipsList.isEmpty();
    }
    // Order Summary

    public String getOrderSummary() {
        StringBuilder sb = new StringBuilder();
        sb.append("========================================\n");
        sb.append("           ORDER SUMMARY\n");
        sb.append("========================================\n");

        if (sandwiches.isEmpty()) {
            sb.append("  (No sandwiches)\n");
        } else {
            sb.append(String.format("SANDWICHES (%d):%n", sandwiches.size()));
            for (int i = 0; i < sandwiches.size(); i++) {
                sb.append(String.format("  --- Sandwich #%d ---%n", i + 1));
                sb.append(sandwiches.get(i).getSummary());
            }
        }

        if (!drinks.isEmpty()) {
            sb.append("DRINKS:\n");
            drinks.forEach(d -> sb.append(d.getSummary()));
        }

        if (!chipsList.isEmpty()) {
            sb.append("CHIPS:\n");
            chipsList.forEach(c -> sb.append(c.getSummary()));
        }

        sb.append("----------------------------------------\n");
        sb.append(String.format("  TOTAL: $%.2f%n", getTotal()));
        sb.append("========================================\n");
        return sb.toString();
    }

}
 