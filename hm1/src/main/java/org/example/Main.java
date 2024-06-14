package org.example;
import java.util.*;
import java.util.HashMap;

public class Main {
    private static final Map<String, Product> products = new HashMap<>();


    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        String command;

        while (true) {
            System.out.print("> ");
            command = scanner.nextLine().trim();

            if(command.equalsIgnoreCase("exit")) {
                break;
            } else if (command.startsWith("create")) {
                createProduct(command.substring(7).trim());
            } else if (command.startsWith("update")) {
                updateProducts(command.substring(7).trim());
            } else if (command.startsWith("read")) {
                readProducts();
            } else if (command.startsWith("delete")) {
                deleteProduct(command.substring(7).trim());
            } else {
                System.out.println("Unknown command");
            }
        }

    }

    private static void createProduct(String data) {
        try {
            String[] parts = data.split(" ", 4);
            if (parts.length < 4) {
                System.out.println("Invalid command format. Usage: create $артикул $название $цена $количество");
                return;
            }
            String id = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);

            if (products.containsKey(id)) {
                System.out.println("Product with this ID already exists.");
                return;
            }

            Product product = new Product(id, name, price, quantity);

            products.put(id, product);
            System.out.println("Product created successfully.");
        } catch (Exception e) {
            System.out.println("Error creating product: " + e.getMessage());
        }
    }

    private static void readProducts() {
        if (products.isEmpty()) {
            System.out.println("No products found.");
            return;
        }

        System.out.printf("%-10s %-20s %-10s %-10s%n", "Артикул", "Название", "Цена", "Количество");
        for (Product product : products.values()) {
            System.out.println(product);
        }
    }

    private static void updateProducts(String data) {
        try {
            String[] parts = data.split(" ", 4);

            if (parts.length < 4) {
                System.out.println("Invalid command format. Usage: create $артикул $название $цена $количество");
                return;
            }
            String id = parts[0];
            String name = parts[1];
            double price = Double.parseDouble(parts[2]);
            int quantity = Integer.parseInt(parts[3]);

            Product product = products.get(id);

            if (product == null) {
                System.out.println("Product not found");
                return;
            }

            product.setName(name);
            product.setPrice(price);
            product.setQuantity(quantity);
            System.out.println("Product update successfully.");
        } catch (Exception e) {
            System.out.println("Error updating product: " + e.getMessage());
        }
    }

    private static void deleteProduct(String id) {
        Product removed = products.remove(id);

        if (removed == null) {
            System.out.println("Product not found.");
        } else {
            System.out.println("Deleted successfully.");
        }
    }
}