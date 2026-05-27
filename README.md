#  DELI-cious POS

> A command-line Point of Sale application for a custom sandwich shop built with Java and OOP principles.


## About the Project

DELI-cious is a fully interactive CLI ordering system that lets customers customize sandwiches, add drinks and chips, review their order, and receive a timestamped receipt — all from the terminal. It replaces a paper-based ordering process with an automated, object-oriented Java application.

---

## Features

- **Custom Sandwiches** — choose bread (white, wheat, rye, wrap), size (4", 8", 12"), meats, cheeses, regular toppings, sauces, and sides
- **Premium Topping Pricing** — meats and cheeses are priced by sandwich size; extras cost extra
- **Regular Toppings & Sauces** — lettuce, peppers, onions, tomatoes, jalapeños, cucumbers, pickles, guacamole, mushrooms, mayo, mustard, ketchup, ranch, thousand islands, vinaigrette — all included
- **Drinks** — small ($2.00), medium ($2.50), large ($3.00) with custom flavor
- **Chips** — flat $1.50, multiple varieties
- **Order Validation** — zero-sandwich orders require at least a drink or chips
- **Receipt Generation** — each completed order saves a `yyyyMMdd-HHmmss.txt` file to `src/main/resources/receipts/`

---

## Pricing Guide

| Item | 4" | 8" | 12" |
|---|---|---|---|
| Bread (base) | $5.50 | $7.00 | $8.50 |
| Meat | $1.00 | $2.00 | $3.00 |
| Extra Meat | $0.50 | $1.00 | $1.50 |
| Cheese | $0.75 | $1.50 | $2.25 |
| Extra Cheese | $0.30 | $0.60 | $0.90 |
| Regular Toppings | FREE | FREE | FREE |
| Sauces / Sides | FREE | FREE | FREE |

| Other | Price |
|---|---|
| Drink (Small) | $2.00 |
| Drink (Medium) | $2.50 |
| Drink (Large) | $3.00 |
| Chips | $1.50 |

---

## Class Diagram

### Package Structure

```
com.pluralsight
├── Application.java          ← Entry point (main method)
├── ui/
│   └── UserInterface.java    ← All CLI menus & input handling
├── models/
│   ├── Order.java            ← Aggregate root for order items
│   ├── Sandwich.java         ← Core product with pricing logic
│   ├── Topping.java          ← MEAT / CHEESE / REGULAR / SAUCE / SIDE
│   ├── Drink.java            ← Size-based pricing
│   └── Chips.java            ← Flat-rate $1.50
└── util/
    └── ReceiptWriter.java    ← Saves receipt via BufferedWriter
```

---

## How to Run

### Prerequisites
- Java 17+
- Maven 3.8+

### Build & Run
```bash
# Clone the repo
git clone https://github.com/YOUR_USERNAME/deli-cious.git
cd deli-cious

# Build with Maven
mvn clean package

# Run
java -jar target/deli-cious-1.0-SNAPSHOT.jar
```

### Or run directly in IntelliJ IDEA
Open the project, right-click `Application.java` → Run.

---

## Screens

| Screen | Description |
|---|---|
| 🏠Home Screen | Start a new order or exit |
| 🧺 Order Screen | Add sandwich / drink / chips, checkout, or cancel |
| 🥖 Add Sandwich | Step-by-step sandwich builder |
| 🥤 Add Drink | Pick size and flavor |
| 🍟 Add Chips | Pick chip variety |
| ✅ Checkout | Review order summary, confirm or cancel |

---

## OOP Concepts Used

- **Encapsulation** — each class manages its own state and exposes clean public methods
- **Single Responsibility** — `UserInterface` handles I/O, `Order` manages items, `ReceiptWriter` handles file output
- **Enumerations** — `ToppingType`, `Drink.Size` make invalid states unrepresentable
- **Composition** — `Order` contains `Sandwich`, `Drink`, and `Chips`; `Sandwich` contains `Topping` objects

---

## Git Branching Strategy

This project uses a feature branch workflow:

```bash
git checkout -b feature/your-feature-name
# ... make changes and commit ...
git checkout main
git merge feature/your-feature-name
git push origin main
```

