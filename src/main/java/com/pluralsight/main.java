package com.pluralsight;

// Imports the UserInterface class that handles customer interaction.
import com.pluralsight.ui.UserInterface;


 // Entry point of the DELI-cious application.
 // Starts the program and launches the user interface.

public class main {

     // Main method where the application execution begins.
    public static void main(String[] args) {

        // Create a new UserInterface object.
        UserInterface ui = new UserInterface();

        // Start the application.
        ui.run();
    }
}