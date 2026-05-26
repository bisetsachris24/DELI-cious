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
        this.drinks     = new ArrayList<>();
        this.chipsList  = new ArrayList<>();
    }
}
 