package com.pluralsight.ui;

import com.pluralsight.models.*;
import com.pluralsight.util.ReceiptWriter;

import java.util.List;
import java.util.Scanner;


// Handles all user-facing menus and input prompts for the DELI-cious POS system.

public class UserInterface {

    private Scanner scanner;


    // Available options (for display)

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

    public UserInterface() {
        this.scanner = new Scanner(System.in);
    }

    // Main run loop

    public void run() {
        boolean running = true;
        while (running) {
            int choice = showHomeScreen();
            switch (choice) {
                case 1 -> processOrder();
                case 0 -> {
                    System.out.println("\nThank you for visiting DELI-cious! Have a delicious day!");
                    running = false;
                }
                default -> System.out.println("Invalid choice. Please try again.");
            }
        }
    }

    // Home Screen

    // Displays the starting menu of the application.
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
    // Handles the full customer ordering experience.

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

    // Order Screen
    // Displays the customer's current order and menu options.
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
        System.out.println("  5) Add Signature Sandwich  ★");
        System.out.println("  0) Cancel Order");
        System.out.print("\nEnter your choice: ");
        return readInt();
    }


    // Add Sandwich
    // Handles sandwich customization and topping selection.
    private void addSandwich(Order order) {
        System.out.println("\n===== BUILD YOUR SANDWICH =====");

        // 1. Bread
        System.out.println("\nSelect your bread:");
        for (int i = 0; i < BREAD_TYPES.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, BREAD_TYPES[i]);
        }
        System.out.print("Choice: ");
        int breadChoice = readInt();
        String bread = (breadChoice >= 1 && breadChoice <= BREAD_TYPES.length)
                ? BREAD_TYPES[breadChoice - 1] : "white";

        // 2. Size
        System.out.println("\nSelect sandwich size:");
        System.out.println("  1) 4\" - $5.50");
        System.out.println("  2) 8\" - $7.00");
        System.out.println("  3) 12\" - $8.50");
        System.out.print("Choice: ");
        int sizeChoice = readInt();
        int size = switch (sizeChoice) {
            case 1 -> 4;
            case 2 -> 8;
            case 3 -> 12;
            default -> 8;
        };

        // 3. Toasted
        System.out.print("\nWould you like it toasted? (yes/no): ");
        boolean toasted = readYesNo();

        Sandwich sandwich = new Sandwich(bread, size, toasted);
        // 4. Meats
        promptForMeats(sandwich, size);

        // 5. Cheeses
        promptForCheeses(sandwich, size);

        // 6. Regular toppings
        promptForToppings(sandwich);

        // 7. Sauces
        promptForSauces(sandwich);

        // 8. Sides
        promptForSides(sandwich);

        order.addSandwich(sandwich);
        System.out.println("\nSandwich added to order!");
        System.out.println(sandwich.getSummary());
    }

    // Meat Selection
    // Allows customers to add meats and extra meat options.
    private void promptForMeats(Sandwich sandwich, int size) {
        System.out.println("\nAvailable meats (enter 0 when done):");
        for (int i = 0; i < MEATS.length; i++) {
            System.out.printf("  %d) %s ($%.2f | extra +$%.2f)%n",
                    i + 1, MEATS[i],
                    getMeatPrice(size, false),
                    getMeatPrice(size, true));
        }
        // Continue allowing meat selection until user enters 0
        while (true) {
            System.out.print("Add meat (0 to skip/done): ");
            int choice = readInt();
            if (choice == 0) break;
            if (choice < 1 || choice > MEATS.length) {
                System.out.println("Invalid choice.");
                continue;
            }
            // Retrieve selected meat.
            String meat = MEATS[choice - 1];
            System.out.print("Extra " + meat + "? (yes/no): ");
            boolean extra = readYesNo();
            sandwich.addMeat(meat, extra);
            System.out.println(meat + (extra ? " (extra)" : "") + " added.");
        }
    }

    // Cheese Selection
    // Allows customers to add cheeses and extra cheese options.
    private void promptForCheeses(Sandwich sandwich, int size) {
        System.out.println("\nAvailable cheeses (enter 0 when done):");
        for (int i = 0; i < CHEESES.length; i++) {
            System.out.printf("  %d) %s ($%.2f | extra +$%.2f)%n",
                    i + 1, CHEESES[i],
                    getCheesePrice(size, false),
                    getCheesePrice(size, true));
        }
        while (true) {
            System.out.print("Add cheese (0 to skip/done): ");
            int choice = readInt();
            if (choice == 0) break;
            if (choice < 1 || choice > CHEESES.length) {
                System.out.println("Invalid choice.");
                continue;
            }
            String cheese = CHEESES[choice - 1];
            System.out.print("Extra " + cheese + "? (yes/no): ");
            boolean extra = readYesNo();
            sandwich.addCheese(cheese, extra);
            System.out.println(cheese + (extra ? " (extra)" : "") + " added.");
        }
    }

    // Regular Toppings Selection
// Allows customers to add included sandwich toppings.
    private void promptForToppings(Sandwich sandwich) {
        System.out.println("\nRegular toppings (included, enter 0 when done):");
        for (int i = 0; i < TOPPINGS.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, TOPPINGS[i]);
        }
        while (true) {
            System.out.print("Add topping (0 to skip/done): ");
            int choice = readInt();
            if (choice == 0) break;
            // Validate menu selection
            if (choice < 1 || choice > TOPPINGS.length) {
                System.out.println("Invalid choice.");
                continue;
            }
            sandwich.addTopping(TOPPINGS[choice - 1]);
            System.out.println(TOPPINGS[choice - 1] + " added.");
        }
    }

    // Sauce Selection
// Allows customers to add included sauces.
    private void promptForSauces(Sandwich sandwich) {
        System.out.println("\nSauces (included, enter 0 when done):");
        for (int i = 0; i < SAUCES.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, SAUCES[i]);
        }
        while (true) {
            System.out.print("Add sauce (0 to skip/done): ");
            int choice = readInt();
            if (choice == 0) break;
            if (choice < 1 || choice > SAUCES.length) {
                System.out.println("Invalid choice.");
                continue;
            }
            sandwich.addSauce(SAUCES[choice - 1]);
            System.out.println(SAUCES[choice - 1] + " added.");
        }
    }

    // Side Selection
// Allows customers to add included side options.
    private void promptForSides(Sandwich sandwich) {
        // Display available side options.
        System.out.println("\nSides (included, enter 0 when done):");
        for (int i = 0; i < SIDES.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, SIDES[i]);
        }
        // Continue allowing side selection until user enters 0.
        while (true) {
            System.out.print("Add side (0 to skip/done): ");
            int choice = readInt();
            if (choice == 0) break;
            if (choice < 1 || choice > SIDES.length) {
                System.out.println("Invalid choice.");
                continue;
            }
            sandwich.addSide(SIDES[choice - 1]);
            System.out.println(SIDES[choice - 1] + " added.");
        }
    }


    // Add Drink
// Drink Ordering
// Allows customers to add drinks to their order.
    private void addDrink(Order order) {
        System.out.println("\n===== ADD A DRINK =====");
        System.out.println("Select size:");
        System.out.println("  1) Small  - $2.00");
        System.out.println("  2) Medium - $2.50");
        System.out.println("  3) Large  - $3.00");
        System.out.print("Choice: ");
        int sizeChoice = readInt();
        Drink.Size size = switch (sizeChoice) {
            case 1 -> Drink.Size.SMALL;
            case 2 -> Drink.Size.MEDIUM;
            case 3 -> Drink.Size.LARGE;
            default -> Drink.Size.MEDIUM;
        };

        System.out.print("Enter drink flavor: ");
        String flavor = scanner.nextLine().trim();
        if (flavor.isEmpty()) flavor = "Cola";

        order.addDrink(new Drink(size, flavor));
        System.out.println("Drink added!");
    }


    // Add Chips
// Chips Ordering
// Allows customers to add chips to their order.

    private void addChips(Order order) {
        System.out.println("\n===== ADD CHIPS - $1.50 =====");
        for (int i = 0; i < CHIP_TYPES.length; i++) {
            System.out.printf("  %d) %s%n", i + 1, CHIP_TYPES[i]);
        }
        System.out.print("Choice: ");
        int choice = readInt();
        String chipType = (choice >= 1 && choice <= CHIP_TYPES.length)
                ? CHIP_TYPES[choice - 1] : "Classic";

        order.addChips(new Chips(chipType));
        System.out.println(chipType + " chips added!");
    }


    // Checkout Process
// Validates the order and handles confirmation.


    private boolean checkout(Order order) {
        if (!order.isValid()) {
            System.out.println("\nYour order is empty! Add at least one item before checking out.");
            return false;
        }

        // Validation: 0-sandwich order must have chips or drink
        if (order.getSandwiches().isEmpty()
                && order.getDrinks().isEmpty()
                && order.getChipsList().isEmpty()) {
            System.out.println("\nPlease add at least one item to your order.");
            return false;
        }

        System.out.println("\n" + order.getOrderSummary());
        System.out.println("  1) Confirm Order");
        System.out.println("  0) Cancel Order");
        System.out.print("Choice: ");
        int choice = readInt();

        if (choice == 1) {
            ReceiptWriter.saveReceipt(order);
            System.out.println("\nOrder confirmed! Thank you for choosing DELI-cious!");
            return true;
        } else {
            System.out.println("\nOrder cancelled.");
            return true; // still return to home screen
        }
    }


    //Helper Input Validation
// Safely reads integer input from the user.
    private int readInt() {
        while (true) {
            try {
                String line = scanner.nextLine().trim();
                return Integer.parseInt(line);
            } catch (NumberFormatException e) {
                System.out.print("Please enter a valid number: ");
            }
        }
    }

    // Yes/No Input Validation
// Reads and validates yes/no responses.

    private boolean readYesNo() {
        while (true) {
            String input = scanner.nextLine().trim().toLowerCase();
            if (input.equals("yes") || input.equals("y")) return true;
            if (input.equals("no") || input.equals("n")) return false;
            System.out.print("Please enter yes or no: ");
        }
    }

    // Price helper (for display only)
// Meat Pricing Helper
// Returns meat pricing based on size and extra option.

    private double getMeatPrice(int size, boolean extra) {
        if (extra) {
            return switch (size) {
                case 4 -> 0.50;
                case 8 -> 1.00;
                default -> 1.50;
            };
        } else {
            return switch (size) {
                case 4 -> 1.00;
                case 8 -> 2.00;
                default -> 3.00;
            };
        }
    }

    // Cheese Pricing Helper
// Returns cheese pricing based on size and extra option.
    private double getCheesePrice(int size, boolean extra) {
        if (extra) {
            return switch (size) {
                case 4 -> 0.30;
                case 8 -> 0.60;
                default -> 0.90;
            };
        } else {
            return switch (size) {
                case 4 -> 0.75;
                case 8 -> 1.50;
                default -> 2.25;
            };
        }
    }
}