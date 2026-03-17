package Inventory_Shop;

import java.util.ArrayList;
import java.util.Scanner;

public class Inventory {
    private ArrayList<Product> products;
    private Scanner scanner;

    public Inventory() {
        products = new ArrayList<>();
        scanner = new Scanner(System.in);
    }

    public void addProduct() {
        System.out.println("\n--- Add New Product ---");

        System.out.print("Enter Product ID: ");
        int id = scanner.nextInt();
        scanner.nextLine(); // Consume newline

        // Check if ID already exists
        for (Product p : products) {
            if (p.getId() == id) {
                System.out.println(" Product with ID " + id + " already exists!");
                return;
            }
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter Price: $");
        double price = scanner.nextDouble();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        Product newProduct = new Product(id, name, price, quantity);
        products.add(newProduct);

        // AUTO-SAVE TO JSON AFTER ADDING
        FileHandler.saveProducts(products);

        System.out.println(" Product added successfully!");
    }

    public void viewProducts() {
        System.out.println("\n--- Current Inventory ---");
        if (products.isEmpty()) {
            System.out.println(" Inventory is empty. Add some products first!");
        } else {
            System.out.println("Total Products: " + products.size());
            System.out.println("-".repeat(60));
            for (Product product : products) {
                System.out.println(product);
            }
            System.out.println("-".repeat(60));
        }
    }

    public void deleteProduct() {
        System.out.println("\n--- Delete Product ---");
        if (products.isEmpty()) {
            System.out.println(" Inventory is empty. Nothing to delete!");
            return;
        }

        System.out.print("Enter Product ID to delete: ");
        int id = scanner.nextInt();

        boolean removed = products.removeIf(product -> product.getId() == id);

        if (removed) {
            //  AUTO-SAVE TO JSON AFTER DELETING
            FileHandler.saveProducts(products);
            System.out.println("Product with ID " + id + " deleted successfully!");
        } else {
            System.out.println(" Product with ID " + id + " not found!");
        }
    }

    // 🧾 OPTIONAL: Method to generate receipt for a product
    public void generateReceipt() {
        System.out.println("\n--- Generate Receipt ---");
        if (products.isEmpty()) {
            System.out.println(" No products to generate receipt for!");
            return;
        }

        viewProducts();

        System.out.print("Enter Product ID for receipt: ");
        int id = scanner.nextInt();

        System.out.print("Enter quantity purchased: ");
        int qty = scanner.nextInt();

        // Find the product
        for (Product p : products) {
            if (p.getId() == id) {
                FileHandler.saveReceipt(p, qty);
                return;
            }
        }

        System.out.println(" Product not found!");
    }
}
