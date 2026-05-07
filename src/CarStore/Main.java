package CarStore;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        inventory myInventory = new inventory();
        Scanner scanner = new Scanner(System.in);
        engineSpecs basicEngine = new engineSpecs("V6", 300,"Gas");

        // CARS GET ADDED HERE

        // Toyota
        myInventory.addVehicle(new sedan("Toyota", "Camry", 2018, 19000, basicEngine, "2018_Toyota_Camry_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Corolla", 2019, 18000, basicEngine, "2019_Toyota_Corolla_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Toyota", "Yaris", 2007, 16000, basicEngine, "2007_Toyota_Yaris_Trans_Front.png", 4));

        // Honda
        myInventory.addVehicle(new sedan("Honda", "Civic", 2023, 24840, basicEngine, "2023_Honda_Civic_Trans_Front.png", 4));

        // Mercedes
        myInventory.addVehicle(new sedan("Mercedes Benz", "A-Class", 2018, 24000, basicEngine, "2018_MercedesBenz_A-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "C-Class", 2018, 50000, basicEngine, "2018_MeredesBenz_C-Class_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "S-Class Coupe (S63 AMG)", 2020, 115000, basicEngine, "2020_MercedesBenz_S-ClassCoupeS63AMG_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Mercedes Benz", "Maybach (S600)", 2017, 190000, basicEngine, "2017_Mercedes-Maybach_Maybach S 600_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Mercedes Benz", "Evo II", 1990, 80000, basicEngine, "1990_Mercedes-Benz_Evo II_Trans_Front.png", 4));

        // Daewoo
        myInventory.addVehicle(new sedan("Daewoo", "Lanos Hatchback", 2002, 1450, basicEngine, "2002_Daewoo_Lanos_Hatchback_Trans_Front.png", 4));

        // Hyundai
        myInventory.addVehicle(new sedan("Hyundai", "Accent", 2003, 1700, basicEngine, "2003_Hyundai_Accent_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Hyundai", "Tucson", 2018, 14000, basicEngine, "2018_Hyundai_Tucson_Trans_Front.png", 4));

        // Chevrolet
        myInventory.addVehicle(new sedan("Chevrolet", "Prisma", 2017, 13400, basicEngine, "2017_Chevrolet_Prisma_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "Aveo", 2010, 2975, basicEngine, "2010_Chevrolet_Aveo_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Chevrolet", "CamaroSS", 2015, 22000, basicEngine, "2015_Chevrolet_Camaro_Trans_Front.png", 2));
        myInventory.addVehicle(new sedan("Chevrolet", "Cruze", 2012, 18000, basicEngine, "2012_Chevrolet_Cruze_Trans_Front.png", 4));

        // Audi
        myInventory.addVehicle(new sedan("Audi", "A6 Sedan", 2018, 29000, basicEngine, "2018_Audi_A6_Sedan_Trans_Front.png", 4));
        myInventory.addVehicle(new sedan("Audi", "RS3", 2017, 34000, basicEngine, "2017_Audi_RS3_Trans_Front.png", 4));

        // Porsche
        myInventory.addVehicle(new sedan("Porsche", "911 Turbo S (Type 911.2)", 2017, 185000, basicEngine, "2018_Porsche_911TS_Trans_Front.png", 2));

        // Lada
        myInventory.addVehicle(new sedan("Lada", "2107", 1982, 1700, basicEngine, "2107_Lada_2107_Trans_Front.png", 4));

        // Mazda
        myInventory.addVehicle(new sedan("Mazda", "Mazda6", 2015, 10000, basicEngine, "2015_Mazda_Mazda6_Trans_Front.png", 4));

        // BMW
        myInventory.addVehicle(new sedan("BMW", "X6", 2019, 75000, basicEngine, "2019_BMW_X6_Trans_Front.png", 4));

        // Volkswagen
        myInventory.addVehicle(new sedan("Volkswagen", "Passat", 2018, 18500, basicEngine, "2018_Wolkswagen_Passat_Trans_Front.png", 4));

        // Rolls-Royce
        myInventory.addVehicle(new sedan("Rolls Royce", "Ghost I", 2026, 418000, basicEngine, "2026_RollsRoyce_Ghost_I_Trans_Front.png", 4));

        // Renault
        myInventory.addVehicle(new sedan("Renault", "Megane", 2018, 20000, basicEngine, "2018_Renault_Megane_Trans_Front.png", 4));


        // CARS GET ADDED HERE I SAID ^^^



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

                        for (vehicle v : myInventory.getVehicleList()) {
                            System.out.println(v.getDetails());
                        }
                        break;
                    case 2:
                        System.out.print("Enter model name to search: ");
                        String modelName = scanner.nextLine();
                        vehicle found = myInventory.searchForMe(modelName);
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