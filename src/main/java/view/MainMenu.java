package main.java.view;

import main.java.trees.TravelerTree;
import main.java.trees.TripTree;
import main.java.tools.ScreenTools;
import main.java.data.ItemTraveler;
import main.java.data.ItemTrip;
import main.java.tools.IDGenerator;

import java.util.Scanner;
import java.util.Random;

public class MainMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerTree travelerTree = new TravelerTree();
    private TripTree tripTree = new TripTree();
    private static final String[] DESTINATIONS = {
            "Paris", "London", "New York", "Tokyo", "Berlin",
            "Sydney", "Rome", "Moscow", "Dubai", "Toronto",
            "Barcelona", "Amsterdam", "Lisbon", "Dublin", "Prague"
    };
    private static final String[] NAMES = {
            "John", "Jane", "Robert", "Emily", "Michael",
            "Sarah", "David", "Anna", "James", "Laura",
            "William", "Sophia", "Charles", "Olivia", "Thomas"
    };
    private static final String[] LASTNAMES = {
            "Smith", "Johnson", "Williams", "Brown", "Jones",
            "Garcia", "Miller", "Davis", "Rodriguez", "Martinez",
            "Hernandez", "Lopez", "Gonzalez", "Wilson", "Anderson"
    };

    public void mainMenu() {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("       Travel Management");
            System.out.println("=================================");
            System.out.println("1. Gerenciar Viajantes");
            System.out.println("2. Gerenciar Destinos");
            System.out.println("3. Gerenciar Viagens");
            System.out.println("4. Carregar Dados Modelos");
            System.out.println("0. Sair");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    TravelerMenu travelerMenu = new TravelerMenu(travelerTree, this);
                    travelerMenu.displayMenu();
                    break;
                case 2:
                    TripMenu tripMenu = new TripMenu(tripTree, this);
                    tripMenu.displayMenu();
                    break;
                case 3:
                    RelationMenu relationMenu = new RelationMenu(travelerTree, tripTree, this);
                    relationMenu.displayMenu();
                    break;
                case 4:
                    loadSampleData();
                    break;
                case 0:
                    System.out.println("Saindo do programa...");
                    System.exit(0);
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void loadSampleData() {
        Random random = new Random();

        // Generate 15 sample trips
        for (int i = 0; i < 15; i++) {
            String destination = DESTINATIONS[random.nextInt(DESTINATIONS.length)];
            String date = generateRandomDate();
            ItemTrip trip = new ItemTrip(IDGenerator.generateTripID(tripTree), date, destination);
            tripTree.insert(trip);
        }

        // Generate 30 sample travelers
        for (int i = 0; i < 30; i++) {
            String name = NAMES[random.nextInt(NAMES.length)];
            String lastname = LASTNAMES[random.nextInt(LASTNAMES.length)];
            int age = 18 + random.nextInt(63); // Age between 18 and 80
            ItemTraveler traveler = new ItemTraveler();
            traveler.setTravelerId(IDGenerator.generateTravelID(travelerTree));
            traveler.setName(name);
            traveler.setLastname(lastname);
            traveler.setAge(age);
            travelerTree.insert(traveler);
        }

        System.out.println("Dados modelos carregados com sucesso!");
        pause();
    }

    private String generateRandomDate() {
        Random random = new Random();
        int year = 2024;
        int month = 11 + random.nextInt(2); // November or December 2024
        int day = 1 + random.nextInt(30); // Any day of the month
        return String.format("%04d-%02d-%02d", year, month, day);
    }

    private void pause() {
        System.out.println("Pressione qualquer tecla para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
