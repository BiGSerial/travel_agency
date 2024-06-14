package main.java.view;

import java.util.Scanner;
import main.java.data.ItemTraveler;
import main.java.trees.TravelerTree;
import main.java.tools.IDGenerator;
import main.java.models.TravelerNode;

public class TravelerMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerTree travelerTree = new TravelerTree();

    public void displayMenu() {
        while (true) {
            clearScreen();
            System.out.println("Traveler Management");
            System.out.println("1. Register Traveler");
            System.out.println("2. Search Traveler");
            System.out.println("3. Remove Traveler");
            System.out.println("4. Edit Traveler");
            System.out.println("5. Back to Main Menu");
            System.out.print("Select an option: ");

            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    registerTraveler();
                    break;
                case 2:
                    searchTraveler();
                    break;
                case 3:
                    removeTraveler();
                    break;
                case 4:
                    editTraveler();
                    break;
                case 5:
                    return;
                default:
                    System.out.println("Invalid option. Please try again.");
                    pause();
            }
        }
    }

    public void listTravelers() {
        clearScreen();
        System.out.println("List of Travelers:");
        ItemTraveler[] travelers = travelerTree.inOrder();
        for (ItemTraveler traveler : travelers) {
            System.out.println(
                    "ID: " + traveler.getTravelerId() + ", Name: " + traveler.getName() + " " + traveler.getLastname());
        }
        pause();
    }

    private void registerTraveler() {
        clearScreen();
        System.out.println("Register a New Traveler");

        ItemTraveler traveler = new ItemTraveler();
        traveler.setTravelerId(IDGenerator.generateTravelID(travelerTree));
        System.out.print("Enter First Name: ");
        traveler.setName(scanner.next());
        System.out.print("Enter Last Name: ");
        traveler.setLastname(scanner.next());
        System.out.print("Enter Age: ");
        traveler.setAge(scanner.nextInt());

        travelerTree.insert(traveler);
        System.out.println("Traveler Registered Successfully!");
        pause();
    }

    private void searchTraveler() {
        clearScreen();
        System.out.println("Search Traveler");
        System.out.print("Enter Traveler ID: ");
        int id = scanner.nextInt();

        TravelerNode node = travelerTree.searchNode(id);
        if (node != null) {
            ItemTraveler traveler = node.getInfo();
            System.out.println("Traveler Found: " + traveler.getName() + " " + traveler.getLastname() + ", Age: "
                    + traveler.getAge());
        } else {
            System.out.println("Traveler not found.");
        }
        pause();
    }

    private void removeTraveler() {
        clearScreen();
        System.out.println("Remove Traveler");
        System.out.print("Enter Traveler ID: ");
        int id = scanner.nextInt();

        if (travelerTree.remove(id)) {
            System.out.println("Traveler Removed Successfully!");
        } else {
            System.out.println("Traveler not found.");
        }
        pause();
    }

    private void editTraveler() {
        clearScreen();
        System.out.println("Edit Traveler");
        System.out.print("Enter Traveler ID: ");
        int id = scanner.nextInt();

        TravelerNode node = travelerTree.searchNode(id);
        if (node != null) {
            ItemTraveler traveler = node.getInfo();
            System.out.println("Editing Traveler: " + traveler.getName() + " " + traveler.getLastname() + ", Age: "
                    + traveler.getAge());
            System.out.print("Enter New First Name (Leave blank to keep current): ");
            String name = scanner.next();
            if (!name.isEmpty()) {
                traveler.setName(name);
            }
            System.out.print("Enter New Last Name (Leave blank to keep current): ");
            String lastname = scanner.next();
            if (!lastname.isEmpty()) {
                traveler.setLastname(lastname);
            }
            System.out.print("Enter New Age (Enter 0 to keep current): ");
            int age = scanner.nextInt();
            if (age > 0) {
                traveler.setAge(age);
            }
            travelerTree.insert(traveler);
            System.out.println("Traveler Edited Successfully!");
        } else {
            System.out.println("Traveler not found.");
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
