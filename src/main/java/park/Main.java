package park;

import java.util.Scanner;

import park.model.ParkingLot;
import park.model.vehicle;
import park.service.BillingService;
import park.service.CleanupData;
import park.service.ParkingService;

public class Main {
    public static void main(String[] args) throws Exception {

        try (Scanner scan = new Scanner(System.in)) {
            ParkingLot lot = new ParkingLot();

            ParkingService ps = new ParkingService();
            BillingService bs = new BillingService();
            CleanupData cd = new CleanupData();

            while (true) {
                System.out.println("1. Park Vehicle\n2. Exit Vehicle\n3. Cleanup Old Data\n4. Exit");
                System.out.print("Choose an option: ");
                if (!scan.hasNextInt()) {
                    System.out.println("Invalid input. Please enter a number.");
                    scan.next();
                    continue;
                }
                int choice = scan.nextInt();
                scan.nextLine(); // consume newline

                try {
                    switch (choice) {
                        case 1:
                            System.out.println("Enter vehicle type (car or bike):");
                            String vehicle_type = scan.nextLine().trim();
                            if (!vehicle_type.equalsIgnoreCase("car") && !vehicle_type.equalsIgnoreCase("bike")) {
                                System.out.println("Invalid vehicle type.");
                                break;
                            }

                            System.out.println("Enter License Plate: ");
                            String licensePlate = scan.nextLine().trim();
                            if (licensePlate.isEmpty()) {
                                System.out.println("License plate cannot be empty.");
                                break;
                            }

                            System.out.println("Enter Vehicle Registration ID:");
                            String vehicle_registration_id = scan.nextLine().trim();

                            System.out.println(vehicle_type + " Owner Name:");
                            String vehicle_owner_name = scan.nextLine().trim();

                            System.out.println("Enter Driver Name:");
                            String driver_name = scan.nextLine().trim();

                            System.out.println("Enter Driver License ID:");
                            String driver_license_id = scan.nextLine().trim();

                            System.out.println("Enter Vehicle Brand:");
                            String vehicle_brand = scan.nextLine().trim();

                            vehicle v = new vehicle(vehicle_type, licensePlate, vehicle_owner_name,
                                    vehicle_registration_id, driver_name, driver_license_id, vehicle_brand);

                            int slot = ps.parkvehicle(vehicle_registration_id, vehicle_type);
                            if (slot == -1) {
                                System.out.println("No available slots for " + vehicle_type);
                            } else {
                                System.out.println(vehicle_type + " parked successfully at slot: " + slot);
                            }
                            break;

                        case 2:
                            System.out.println("Enter License Plate: ");
                            String exit_vehicle = scan.nextLine().trim();
                            bs.exitvehicle(exit_vehicle);
                            break;

                        case 3:
                            System.out.println("Running Cleanup of data in background...");
                            new Thread(() -> {
                                try {
                                    cd.cleanUp();
                                    System.out.println("Cleanup completed.");
                                } catch (Exception e) {
                                    System.out.println("Error during cleanup: " + e.getMessage());
                                }
                            }).start();
                            break;

                        case 4:
                            System.out.println("Exiting application.");
                            return;

                        default:
                            System.out.println("Invalid choice. Please select a valid option.");
                            break;
                    }

                } catch (Exception e) {
                    System.out.println("Operation failed: " + e.getMessage());
                }
            }
        }
    }
}
