package Inventory_Shop;
import java.util.Scanner;
public class Main {


        public static void main(String[] args) {
            Scanner scanner = new Scanner(System.in);
            Inventory inventory = new Inventory();
            boolean running = true;

            System.out.println(" Welcome to Shop Inventory System");
            System.out.println("=".repeat(40));

            long startTime = System.nanoTime(); // Start timing

            while (running) {
                displayMenu();
                System.out.print("Enter your choice: ");

                int choice = scanner.nextInt();

                switch (choice) {
                    case 1:
                        inventory.addProduct();
                        break;

                    case 2:
                        inventory.viewProducts();
                        break;

                    case 3:
                        inventory.deleteProduct();
                        break;

                    case 4:
                        running = false;
                        System.out.println("\nThank you for using Shop Inventory System!");
                        break;
                    case 5:
                        inventory.generateReceipt();
                        break;

                    default:
                        System.out.println(" Invalid choice! Please try again.");
                }

                System.out.println(); // Empty line for better readability
            }

            long endTime = System.nanoTime();
            double elapsedSeconds = (endTime - startTime) / 1_000_000_000.0;
            System.out.printf("Session Duration: %.2f seconds%n", elapsedSeconds);

            scanner.close();
        }

        private static void displayMenu() {
            System.out.println("\n===== SHOP INVENTORY SYSTEM =====");
            System.out.println("1. ➕ Add Product");
            System.out.println("2. 👁️ View All Products");
            System.out.println("3. ❌ Delete Product");
            System.out.println("4. 🚪 Exit");
            System.out.println("=".repeat(35));
        }
    }


