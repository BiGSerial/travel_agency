package main.java.view;

import java.util.Scanner;
import main.java.data.ItemTrip;
import main.java.trees.TripTree;
import main.java.tools.IDGenerator;

public class TripMenu {
    private Scanner scanner = new Scanner(System.in);
    private TripTree tripTree = new TripTree();

    public void displayMenu() {
        while (true) {
            clearScreen();
            System.out.println("Trip Management");
            System.out.println("1. Register Trip");
            System.out.println("2. Search Trip");
            System.out.println("3. Remove Trip");
            System.out.println("4. Edit Trip");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registerTrip();
                    break;
                case 2:
                    searchTrip();
                    break;
                case 3:
                    removeTrip();
                    break;
                case 4:
                    editTrip();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pause();
            }
        }
    }

    public void listTrips() {
        clearScreen();
        System.out.println("List of Trips:");
        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            System.out.println(
                    "ID: " + trip.getId() + ", Destination: " + trip.getDestination() + ", Date: " + trip.getDate());
        }
        pause();
    }

    private void registerTrip() {
        clearScreen();
        System.out.println("Register a New Trip");

        ItemTrip trip = new ItemTrip();
        trip.setId(IDGenerator.generateTripID(tripTree));
        System.out.print("Enter Traveler ID: ");
        trip.setTravelerId(scanner.nextInt());
        System.out.print("Enter Destination: ");
        trip.setDestination(scanner.next());
        System.out.print("Enter Date (YYYY-MM-DD): ");
        trip.setDate(scanner.next());

        tripTree.insert(trip);
        System.out.println("Trip Registered Successfully!");
        pause();
    }

    private void searchTrip() {
        clearScreen();
        System.out.println("Search Trip");
        System.out.print("Enter Trip ID: ");
        int id = scanner.nextInt();

        if (tripTree.searchNode(id) != null) {
            ItemTrip trip = tripTree.searchNode(id).getInfo();
            System.out.println("Trip Found: ID: " + trip.getId() + ", Destination: " + trip.getDestination()
                    + ", Date: " + trip.getDate());
        } else {
            System.out.println("Trip not found.");
        }
        pause();
    }

    private void removeTrip() {
        clearScreen();
        System.out.println("Remove Trip");
        System.out.print("Enter Trip ID: ");
        int id = scanner.nextInt();

        if (tripTree.remove(id)) {
            System.out.println("Trip Removed Successfully!");
        } else {
            System.out.println("Trip not found.");
        }
        pause();
    }

    private void editTrip() {
        clearScreen();
        System.out.println("Edit Trip");
        System.out.print("Enter Trip ID: ");
        int id = scanner.nextInt();

        if (tripTree.searchNode(id) != null) {
            ItemTrip trip = tripTree.searchNode(id).getInfo();
            System.out.println("Editing Trip: ID: " + trip.getId() + ", Destination: " + trip.getDestination()
                    + ", Date: " + trip.getDate());
            System.out.print("Enter New Traveler ID (Leave blank to keep current): ");
            String travelerId = scanner.next();
            if (!travelerId.isEmpty()) {
                trip.setTravelerId(Integer.parseInt(travelerId));
            }
            System.out.print("Enter New Destination (Leave blank to keep current): ");
            String destination = scanner.next();
            if (!destination.isEmpty()) {
                trip.setDestination(destination);
            }
            System.out.print("Enter New Date (YYYY-MM-DD) (Leave blank to keep current): ");
            String date = scanner.next();
            if (!date.isEmpty()) {
                trip.setDate(date);
            }
            tripTree.insert(trip);
            System.out.println("Trip Edited Successfully!");
        } else {
            System.out.println("Trip not found.");
        }
        pause();
    }

    private void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    private void pause() {
        System.out.println("Press Enter to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
