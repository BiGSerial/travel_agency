package main.java.view;

import main.java.tools.ScreenTools;
import main.java.data.ItemTraveler;
import main.java.data.ItemTrip;
import main.java.models.TravelerNode;
import main.java.models.TripNode;
import main.java.tools.IDGenerator;
import main.java.trees.TravelerTree;
import main.java.trees.TripTree;

import java.util.List;
import java.util.Scanner;

public class TravelerMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerTree travelerTree;
    private TripTree tripTree;
    private MainMenu mainMenu;

    public TravelerMenu(TravelerTree travelerTree, TripTree tripTree, MainMenu mainMenu) {
        this.travelerTree = travelerTree;
        this.tripTree = tripTree;
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {
        while (true) {
            ScreenTools.programTitle();
            printTravelerMenu();
            int choice = scanner.nextInt();
            handleTravelerMenuChoice(choice);
        }
    }

    private void printTravelerMenu() {
        System.out.println("=================================");
        System.out.println("        Manage Travelers");
        System.out.println("=================================");
        System.out.println("1. Register Traveler");
        System.out.println("2. Search Traveler");
        System.out.println("3. List All Travelers (Pre-Order)");
        System.out.println("4. List All Travelers (Post-Order)");
        System.out.println("5. List Trips of a Traveler");
        System.out.println("0. Back to Main Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleTravelerMenuChoice(int choice) {
        switch (choice) {
            case 1:
                newTraveler();
                break;
            case 2:
                searchTravelerMenu();
                break;
            case 3:
                listPreOrder();
                break;
            case 4:
                listPostOrder();
                break;
            case 5:
                listTripsOfTraveler();
                break;
            case 0:
                mainMenu.mainMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void newTraveler() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Register New Traveler");
        System.out.println("=================================");
        System.out.println();
        System.out.print("Enter Traveler Name: ");
        String name = scanner.next();
        System.out.print("Enter Traveler Lastname: ");
        String lastname = scanner.next();
        System.out.print("Enter Traveler Age: ");
        int age = scanner.nextInt();

        ItemTraveler traveler = new ItemTraveler();
        traveler.setTravelerId(IDGenerator.generateTravelID(travelerTree));
        traveler.setName(name);
        traveler.setLastname(lastname);
        traveler.setAge(age);

        if (travelerTree.insert(traveler)) {
            System.out.println("Traveler registered successfully!");
        } else {
            System.out.println("Error registering traveler.");
        }
        pause();
    }

    private void searchTravelerMenu() {
        while (true) {
            ScreenTools.programTitle();
            printSearchTravelerMenu();
            int choice = scanner.nextInt();
            handleSearchTravelerMenuChoice(choice);
        }
    }

    private void printSearchTravelerMenu() {
        System.out.println("=================================");
        System.out.println("        Search Travelers");
        System.out.println("=================================");
        System.out.println("1. List All Travelers");
        System.out.println("2. Search Traveler by Name");
        System.out.println("3. Select Traveler");
        System.out.println("0. Back to Previous Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleSearchTravelerMenuChoice(int choice) {
        switch (choice) {
            case 1:
                listAllTravelers();
                break;
            case 2:
                searchTravelerByName();
                break;
            case 3:
                selectTraveler();
                break;
            case 0:
                displayMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void searchTravelerByName() {
        System.out.print("Enter name pattern (use % as wildcard): ");
        String pattern = scanner.next();
        List<ItemTraveler> travelers = travelerTree.searchTravelersByName(pattern);
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("        Search Results");
        System.out.println("=================================");
        for (ItemTraveler traveler : travelers) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Name: " + traveler.getName() + " "
                    + traveler.getLastname());
        }
        pause();
    }

    private void listAllTravelers() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("        List of Travelers");
        System.out.println("=================================");
        for (ItemTraveler traveler : travelerTree.inOrder()) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Name: " + traveler.getName() + " "
                    + traveler.getLastname());
        }
        pause();
    }

    private void listPreOrder() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("    List of Travelers (Pre-Order)");
        System.out.println("=================================");
        for (ItemTraveler traveler : travelerTree.preOrder()) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Name: " + traveler.getName() + " "
                    + traveler.getLastname());
        }
        pause();
    }

    private void listPostOrder() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("    List of Travelers (Post-Order)");
        System.out.println("=================================");
        for (ItemTraveler traveler : travelerTree.postOrder()) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Name: " + traveler.getName() + " "
                    + traveler.getLastname());
        }
        pause();
    }

    private void listTripsOfTraveler() {
        System.out.print("Enter Traveler ID: ");
        int travelerId = scanner.nextInt();
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Traveler not found.");
            pause();
            return;
        }

        List<ItemTrip> trips = travelerTree.getTripsForTraveler(travelerId, tripTree);
        System.out.println("Trips of Traveler ID " + travelerId + ":");
        for (ItemTrip trip : trips) {
            System.out.println("ID: " + trip.getTripID() + " - Destination: " + trip.getDestination() + " - Date: "
                    + trip.getDate());
        }
        pause();
    }

    private void selectTraveler() {
        System.out.print("Enter Traveler ID: ");
        int travelerId = scanner.nextInt();
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Traveler not found.");
        } else {
            editOrRemoveTravelerMenu(travelerNode.getInfo());
        }
        pause();
    }

    private void editOrRemoveTravelerMenu(ItemTraveler traveler) {
        while (true) {
            ScreenTools.programTitle();
            printEditOrRemoveTravelerMenu(traveler);
            int choice = scanner.nextInt();
            handleEditOrRemoveTravelerMenuChoice(choice, traveler);
        }
    }

    private void printEditOrRemoveTravelerMenu(ItemTraveler traveler) {
        System.out.println("=================================");
        System.out.println("      Manage Traveler");
        System.out.println("=================================");
        System.out.println();
        System.out.println("ID: " + traveler.getTravelerId());
        System.out.println("Name: " + traveler.getName());
        System.out.println("Lastname: " + traveler.getLastname());
        System.out.println("Age: " + traveler.getAge());
        System.out.println();
        System.out.println("Trips:");
        List<ItemTrip> trips = travelerTree.getTripsForTraveler(traveler.getTravelerId(), tripTree);
        for (ItemTrip trip : trips) {
            System.out.println("  ID: " + trip.getTripID() + " - Destination: " + trip.getDestination() + " - Date: "
                    + trip.getDate());
        }
        System.out.println();
        System.out.println("=================================");
        System.out.println("1. Edit Traveler");
        System.out.println("2. Remove Traveler");
        System.out.println("3. Add Trip to Traveler");
        System.out.println("4. Remove Trip from Traveler");
        System.out.println("0. Back to Previous Menu");
        System.out.println("=================================");
        System.out.print("Select an Option: ");
    }

    private void handleEditOrRemoveTravelerMenuChoice(int choice, ItemTraveler traveler) {
        switch (choice) {
            case 1:
                editTraveler(traveler);
                return;
            case 2:
                removeTraveler(traveler);
                return;
            case 3:
                addTripToTraveler(traveler);
                return;
            case 4:
                removeTripFromTraveler(traveler);
                return;
            case 0:
                searchTravelerMenu();
                break;
            default:
                System.out.println("Invalid option. Please try again.");
        }
    }

    private void editTraveler(ItemTraveler traveler) {
        System.out.print("Enter new Traveler Name: ");
        String newName = scanner.next();
        System.out.print("Enter new Traveler Lastname: ");
        String newLastname = scanner.next();
        System.out.print("Enter new Traveler Age: ");
        int newAge = scanner.nextInt();

        traveler.setName(newName);
        traveler.setLastname(newLastname);
        traveler.setAge(newAge);

        System.out.println("Traveler updated successfully!");
        pause();
    }

    private void removeTraveler(ItemTraveler traveler) {
        if (travelerTree.remove(traveler.getTravelerId())) {
            System.out.println("Traveler removed successfully!");
            pause();
            searchTravelerMenu();

        } else {
            System.out.println("Error removing traveler.");
        }
        pause();
    }

    private void addTripToTraveler(ItemTraveler traveler) {
        System.out.print("Enter Trip ID: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Trip not found.");
            pause();
            return;
        }

        ItemTrip trip = tripNode.getInfo();
        if (trip.addTravelerId(traveler.getTravelerId())) {
            System.out.println("Trip added to traveler successfully!");
        } else {
            System.out.println("Trip is already assigned to this traveler.");
        }
        pause();
    }

    private void removeTripFromTraveler(ItemTraveler traveler) {
        System.out.print("Enter Trip ID: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Trip not found.");
            pause();
            return;
        }

        ItemTrip trip = tripNode.getInfo();
        if (trip.getTravelerIds().remove(Integer.valueOf(traveler.getTravelerId()))) {
            System.out.println("Trip removed from traveler successfully!");
        } else {
            System.out.println("Trip not found for this traveler.");
        }
        pause();
    }

    private void pause() {
        System.out.println("Press any key to continue...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
