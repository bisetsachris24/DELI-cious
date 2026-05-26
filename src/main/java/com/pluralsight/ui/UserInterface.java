package com.pluralsight.ui;

import com.pluralsight.models.Order;

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

// Main application loop
// Continuously displays the home screen until the user exits.


    public void run() {

        // Controls whether the application continues running.
        boolean running = true;

        // Keep showing the menu while the program is active.
        while (running) {

            // Display home screen options and get the user's choice.
            int choice = showHomeScreen();

            // Process the selected menu option.
            switch (choice) {

                // Start a new customer order.
                case 1 -> processOrder();

                // Exit the application.
                case 0 -> {

                    // Display goodbye message before closing.
                    System.out.println(
                            "\nThank you for visiting DELI-cious! Have a delicious day!");

                    running = false;
                }

                // Handle invalid menu selections.
                default -> System.out.println(
                        "Invalid choice. Please try again.");
            }
        }
    }
    // Home Screen

    private int showHomeScreen() {
        System.out.println("\n╔══════════════════════════════════════╗");
        System.out.println("║        Welcome to DELI-cious!        ║");
        System.out.println("║     Your Custom Sandwich Shop        ║");
        System.out.println("╚══════════════════════════════════════╝");
        System.out.println("  1) New Order");
        System.out.println("  0) Exit");
        System.out.print("\nEnter your choice: ");
        return readInt();
    }
    // Order flow

    private void processOrder() {
        Order order = new Order();
        boolean ordering = true;

        while (ordering) {
            int choice = showOrderScreen(order);
            switch (choice) {
                case 1 -> addSandwich(order);
                case 2 -> addDrink(order);
                case 3 -> addChips(order);
                case 4 -> {
                    if (checkout(order)) {
                        ordering = false;
                    }
                }
                case 0 -> {
                    System.out.println("\nOrder cancelled. Returning to Home Screen.");
                    ordering = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }
    private int showOrderScreen(Order order) {
        System.out.println("\n----------------------------------------");
        System.out.println("              ORDER SCREEN");
        System.out.println("----------------------------------------");

        // Show current order snapshot (newest first)
        List<Sandwich> sandwiches = order.getSandwiches();
        List<Drink> drinks = order.getDrinks();
        List<Chips> chipsList = order.getChipsList();

        if (!chipsList.isEmpty()) {
            System.out.println("Current Chips:");
            for (int i = chipsList.size() - 1; i >= 0; i--) {
                System.out.print("  " + chipsList.get(i).getSummary());
            }
        }
        if (!drinks.isEmpty()) {
            System.out.println("Current Drinks:");
            for (int i = drinks.size() - 1; i >= 0; i--) {
                System.out.print("  " + drinks.get(i).getSummary());
            }
        }
        if (!sandwiches.isEmpty()) {
            System.out.printf("Current Sandwiches (%d):%n", sandwiches.size());
            for (int i = sandwiches.size() - 1; i >= 0; i--) {
                System.out.printf("  Sandwich #%d:%n", i + 1);
                System.out.print(sandwiches.get(i).getSummary());
            }
        }


        System.out.printf("%nRunning Total: $%.2f%n", order.getTotal());
        System.out.println("----------------------------------------");
        System.out.println("  1) Add Sandwich");
        System.out.println("  2) Add Drink");
        System.out.println("  3) Add Chips");
        System.out.println("  4) Checkout");
        System.out.println("  0) Cancel Order");
        System.out.print("\nEnter your choice: ");
        return readInt();
    }
}
