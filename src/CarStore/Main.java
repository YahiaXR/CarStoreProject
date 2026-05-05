package CarStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        inventory shop = new inventory();
        Scanner scanner = new Scanner(System.in);


        shop.addVehicle(new sedan("Toyota", "Camry", 25000, "camry.jpg", new engineSpecs("V8", 300)));
        shop.addVehicle(new sedan("Toyota", "Yaris", 40000, "camry.jpg", new engineSpecs("V8", 210)));
        shop.addVehicle(new sedan("Toyota", "Corolla", 100000, "camry.jpg", new engineSpecs("V8", 388)));
        shop.addVehicle(new sedan("BYD", "F3", 150000, "camry.jpg", new engineSpecs("V8", 388)));
        shop.addVehicle(new sedan("Lada", "2107", 10000, "camry.jpg", new engineSpecs("V8", 388)));
        shop.addVehicle(new sedan("MG", "7", 1000000, "camry.jpg", new engineSpecs("V8", 405)));

        boolean running = true;

        while (running) {
            System.out.println("\n--- Car Store Terminal Test ---");
            System.out.println("1. Display All Cars");
            System.out.println("2. Search by Model");
            System.out.println("3. Exit");
            System.out.print("Select an option: ");


            try {
                int choice = Integer.parseInt(scanner.nextLine());

                switch (choice) {
                    case 1:
                        System.out.println("\nListing all vehicles:");

                        for (vehicle v : shop.getVehicleList()) {
                            System.out.println(v.getDetails());
                        }
                        break;
                    case 2:
                        System.out.print("Enter model name to search: ");
                        String modelName = scanner.nextLine();
                        vehicle found = shop.searchForMe(modelName);
                        if (found != null) {
                            System.out.println("Found: " + found.getDetails());
                        } else {
                            System.out.println("Vehicle not found.");
                        }
                        break;
                    case 3:
                        running = false;
                        System.out.println("Exiting...");
                        break;
                    default:
                        System.out.println("Invalid choice. Try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Error: Please enter a valid number for the menu choice.");
            }
        }
        scanner.close();
    }
}