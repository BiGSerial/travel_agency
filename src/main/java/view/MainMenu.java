package main.java.view;

import java.util.Scanner;

import main.java.tools.AppConstants;


public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerMenu travelerMenu = new TravelerMenu();
    private TripMenu tripMenu = new TripMenu();

    public void displayMenu() {
        while (true) {
            clearScreen();
            System.out.println(AppConstants.APP_NAME);
            System.out.println("Developed by: " + String.join(", ", AppConstants.DEVELOPERS));
            System.out.println("1. List Travelers");
            System.out.println("2. List Destinations");
            System.out.println("3. Traveler Management");
            System.out.println("4. Trip Management");
            System.out.println("5. Exit");
            System.out.print("Select an option: ");
            
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    travelerMenu.listTravelers();
                    break;
                case 2:
                    tripMenu.listTrips();
                    break;
                case 3:
                    travelerMenu.displayMenu();
                    break;
                case 4:
                    tripMenu.displayMenu();
                    break;
                case 5:
                    System.exit(0);
                default:
                    System.out.println("Invalid option. Please try again.");
                    pause();
            }
        }
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
