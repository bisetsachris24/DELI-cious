package com.pluralsight.util;

import com.pluralsight.models.Order;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class ReceiptWriter {
    // Path where all receipt files will be stored.
    private static final String receipt = "src/main/resources/receipts/";

    public static void saveReceipt(Order order) {
        // Ensure the receipts directory exists
        // If the folder does not exist, create it automatically.
        try {
            // Create the directory structure for receipts.
            Files.createDirectories(Paths.get(receipt));
        } catch (IOException e) {
            System.out.println("Error creating receipts directory: " + e.getMessage());
            return;
        }
        String fileName = receipt + generateTimestamp() + ".txt";

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {
            writer.write("DELI-cious Receipt");
            writer.newLine();
            writer.write("Date/Time: " + LocalDateTime.now()
                    .format(DateTimeFormatter.ofPattern("MM/dd/yyyy HH:mm:ss")));
            writer.newLine();
            writer.newLine();
            writer.write(order.getOrderSummary());
            writer.newLine();
            writer.write("Thank you for choosing DELI-cious!");
            writer.newLine();

            System.out.println("Receipt saved: " + fileName);

        } catch (IOException e) {
            System.out.println("Error saving receipt: " + e.getMessage());
        }
    }

    private static String generateTimestamp() {
    }
}

