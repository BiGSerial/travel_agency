package main.java.view;

import main.java.tools.ScreenTools;
import main.java.data.ItemTrip;
import main.java.models.TravelerNode;
import main.java.models.TripNode;
import main.java.data.ItemTraveler;
import main.java.tools.IDGenerator;
import main.java.trees.TripTree;
import main.java.trees.TravelerTree;

import java.util.List;
import java.util.Scanner;

public class TripMenu {
    private Scanner scanner = new Scanner(System.in);
    private TripTree tripTree;
    private TravelerTree travelerTree;
    private MainMenu mainMenu;

    public TripMenu(TripTree tripTree, TravelerTree travelerTree, MainMenu mainMenu) {
        this.tripTree = tripTree;
        this.travelerTree = travelerTree;
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {
        while (true) {
            ScreenTools.programTitle();
            printTripMenu();
            int choice = scanner.nextInt();
            handleTripMenuChoice(choice);
        }
    }

    private void printTripMenu() {
        System.out.println("=================================");
        System.out.println("         Manage Trips");
        System.out.println("=================================");
        System.out.println("1. Register Trip");
        System.out.println("2. Search Trip");
        System.out.println("0. Back to Main Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleTripMenuChoice(int choice) {
        switch (choice) {
            case 1:
                newTrip();
                break;
            case 2:
                searchTripMenu();
                break;
            case 0:
                mainMenu.mainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void newTrip() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Register New Trip");
        System.out.println("=================================");
        System.out.println();
        System.out.print("Enter Trip Destination: ");
        String destination = scanner.next();
        System.out.print("Enter Trip Date: ");
        String date = scanner.next();

        ItemTrip trip = new ItemTrip(IDGenerator.generateTripID(tripTree), date, destination);
        if (tripTree.insert(trip)) {
            System.out.println("Trip registered successfully!");
        } else {
            System.out.println("Error registering trip.");
        }
        pause();
    }

    private void searchTripMenu() {
        while (true) {
            ScreenTools.programTitle();
            printSearchTripMenu();
            int choice = scanner.nextInt();
            handleSearchTripMenuChoice(choice);
        }
    }

    private void printSearchTripMenu() {
        System.out.println("=================================");
        System.out.println("        Search Trips");
        System.out.println("=================================");
        System.out.println("1. List All Trips");
        System.out.println("2. Select Trip");
        System.out.println("0. Back to Previous Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleSearchTripMenuChoice(int choice) {
        switch (choice) {
            case 1:
                listAllTrips();
                break;
            case 2:
                selectTrip();
                break;
            case 0:
                displayMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void listAllTrips() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("         List of Trips");
        System.out.println("=================================");
        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            System.out.println("ID: " + trip.getTripID() + " - Destination: " + trip.getDestination() + " - Travelers: "
                    + trip.getTotalTravelers());
        }
        pause();
    }

    private void selectTrip() {
        System.out.print("Enter Trip ID: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Trip not found.");
        } else {
            editOrRemoveTripMenu(tripNode.getInfo());
        }
        pause();
    }

    private void editOrRemoveTripMenu(ItemTrip trip) {
        while (true) {
            ScreenTools.programTitle();
            printEditOrRemoveTripMenu(trip);
            int choice = scanner.nextInt();
            handleEditOrRemoveTripMenuChoice(choice, trip);
        }
    }

    private void printEditOrRemoveTripMenu(ItemTrip trip) {
        System.out.println("=================================");
        System.out.println("      Manage Trip");
        System.out.println("=================================");
        System.out.println();
        System.out.println("ID: " + trip.getTripID());
        System.out.println("Destination: " + trip.getDestination());
        System.out.println("Date: " + trip.getDate());
        System.out.println("Travelers: " + trip.getTotalTravelers());
        System.out.println();
        System.out.println("=================================");
        System.out.println("1. Edit Trip");
        System.out.println("2. Remove Trip");
        System.out.println("3. Add Traveler to Trip");
        System.out.println("4. Remove Traveler from Trip");
        System.out.println("5. List Travelers of Trip");
        System.out.println("0. Back to Previous Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleEditOrRemoveTripMenuChoice(int choice, ItemTrip trip) {
        switch (choice) {
            case 1:
                editTrip(trip);
                return;
            case 2:
                removeTrip(trip);
                return;
            case 3:
                addTravelerToTrip(trip);
                return;
            case 4:
                removeTravelerFromTrip(trip);
                return;
            case 5:
                listTravelersOfTrip(trip);
                return;
            case 0:
                searchTripMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void editTrip(ItemTrip trip) {
        System.out.print("Enter new Trip Destination: ");
        String newDestination = scanner.next();
        System.out.print("Enter new Trip Date: ");
        String newDate = scanner.next();

        trip.setDestination(newDestination);
        trip.setDate(newDate);

        System.out.println("Trip updated successfully!");
        pause();
    }

    private void removeTrip(ItemTrip trip) {
        if (tripTree.remove(trip.getTripID())) {
            System.out.println("Trip removed successfully!");
        } else {
            System.out.println("Error removing trip.");
        }
        pause();
    }

    private void addTravelerToTrip(ItemTrip trip) {
        System.out.print("Enter Traveler ID: ");
        int travelerId = scanner.nextInt();
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Traveler not found.");
            pause();
            return;
        }

        if (trip.addTravelerId(travelerId)) {
            System.out.println("Traveler added to trip successfully!");
        } else {
            System.out.println("Traveler is already on this trip.");
        }
        pause();
    }

    private void removeTravelerFromTrip(ItemTrip trip) {
        System.out.print("Enter Traveler ID: ");
        int travelerId = scanner.nextInt();
        if (trip.getTravelerIds().remove(Integer.valueOf(travelerId))) {
            System.out.println("Traveler removed from trip successfully!");
        } else {
            System.out.println("Traveler not found on this trip.");
        }
        pause();
    }

    private void listTravelersOfTrip(ItemTrip trip) {
        List<Integer> travelerIds = trip.getTravelerIds();
        System.out.println("=================================");
        System.out.println(" Travelers List to " + trip.getDestination());
        System.out.println("=================================");
        System.out.println();
        for (int travelerId : travelerIds) {
            TravelerNode travelerNode = travelerTree.searchNode(travelerId);
            if (travelerNode != null) {
                ItemTraveler traveler = travelerNode.getInfo();
                System.out.println("ID: " + traveler.getTravelerId() + " - Name: " + traveler.getName() + " "
                        + traveler.getLastname());
            }
        }
        System.out.println("=================================");
        pause();
    }

    private void pause() {
        System.out.println("Press any key to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
