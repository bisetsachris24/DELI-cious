package com.pluralsight.ui;

import java.util.Scanner;

public class UserInterface {
    // Scanner object used to read user input from the console.
    private Scanner scanner;


    // Available options (for display)
// Available bread choices for sandwiches.
    private static final String[] BREAD_TYPES = {"white", "wheat", "rye", "wrap"};
    private static final String[] MEATS = {"steak", "ham", "salami", "roast beef", "chicken", "bacon"};
    private static final String[] CHEESES = {"american", "provolone", "cheddar", "swiss"};
    private static final String[] TOPPINGS = {"lettuce", "peppers", "onions", "tomatoes",
            "jalapeños", "cucumbers", "pickles", "guacamole", "mushrooms"};
    private static final String[] SAUCES = {"mayo", "mustard", "ketchup", "ranch",
            "thousand islands", "vinaigrette"};
    private static final String[] SIDES = {"au jus", "sauce"};
    private static final String[] CHIP_TYPES = {"Classic", "BBQ", "Sour Cream & Onion",
            "Salt & Vinegar", "Jalapeño"};

    //Creates a Scanner object for reading console input.
    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }
}
