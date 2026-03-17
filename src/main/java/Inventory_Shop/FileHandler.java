package Inventory_Shop;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.ArrayList;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileHandler {

    // Save products to JSON file
    public static void saveProducts(ArrayList<Product> products) {
        try {
            // Create JSON manually
            StringBuilder json = new StringBuilder();
            json.append("[\n");

            for (int i = 0; i < products.size(); i++) {
                Product p = products.get(i);
                json.append("  {\n");
                json.append("    \"id\": ").append(p.getId()).append(",\n");
                json.append("    \"name\": \"").append(p.getName()).append("\",\n");
                json.append("    \"price\": ").append(p.getPrice()).append(",\n");
                json.append("    \"quantity\": ").append(p.getQuantity()).append("\n");
                json.append("  }");

                if (i < products.size() - 1) {
                    json.append(",");
                }
                json.append("\n");
            }
            json.append("]");

            // Save to file
            Files.write(Paths.get("products.json"), json.toString().getBytes());
            System.out.println(" Auto-saved to products.json");

        } catch (Exception e) {
            System.out.println(" Error saving to JSON: " + e.getMessage());
        }
    }

    // Save receipt to TXT file
    public static void saveReceipt(Product product, int quantity) {
        try {
            String filename = "receipt_" +
                    LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyyMMdd_HHmmss")) +
                    ".txt";

            double total = product.getPrice() * quantity;

            List<String> lines = List.of(
                    "=================================",
                    "           SHOP RECEIPT",
                    "=================================",
                    "Date: " + LocalDateTime.now(),
                    "Product ID: " + product.getId(),
                    "Product: " + product.getName(),
                    "Price: $" + String.format("%.2f", product.getPrice()),
                    "Quantity: " + quantity,
                    "Total: $" + String.format("%.2f", total),
                    "=================================",
                    "     Thank you for shopping!",
                    "================================="
            );

            Files.write(Paths.get(filename), lines);
            System.out.println(" Receipt saved as: " + filename);

        } catch (Exception e) {
            System.out.println(" Error saving receipt: " + e.getMessage());
        }
    }
}