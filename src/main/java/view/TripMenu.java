package main.java.view;

import java.util.Scanner;

import main.java.data.ItemTrip;
import main.java.models.TripNode;
import main.java.tools.*;
import main.java.trees.TripTree;

public class TripMenu {

    private Scanner scanner = new Scanner(System.in);
    private TripTree tripTree;
    private MainMenu mainMenu;

    public TripMenu(TripTree tripTree, MainMenu mainMenu) {
        this.tripTree = tripTree;
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {

        Scanner scanner = new Scanner(System.in);

        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("         Gereciar Destinos");
            System.out.println("=================================");
            System.out.println("1. Cadastrar Destino");
            System.out.println("2. Buscar Destino");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

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
    }

    public void newTrip() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("     Cadastrar Novo Destino");
        System.out.println("=================================");
        System.out.println();
        System.out.println("Informe o nome do Destino:");
        System.out.print(">> ");
        String destination = this.scanner.next();
        System.out.println("Informe a Data da Viagem:");
        System.out.print(">> ");
        String date = this.scanner.next();

        ItemTrip trip = new ItemTrip(IDGenerator.generateTripID(this.tripTree), date, destination);
        if (this.tripTree.insert(trip)) {
            System.out.println();
            System.out.println("CADASTRADO COM SUCESSO!");
            System.out.println();
            pause();
            displayMenu();

        } else {
            System.out.println();
            System.out.println("OOPS! Ocorreu algum erro ao cadastrar o destino");
            System.out.println();
            pause();
            displayMenu();
        }

    }

    private void searchTripMenu() {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("        Buscar Destinos");
            System.out.println("=================================");
            System.out.println("1. Listar Todos os Destinos");
            System.out.println("2. Selecionar Destino");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllTrips();
                    break;
                case 2:
                    selectTrip();
                    break;
                case 0:
                    return; // Voltar ao menu anterior
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void listAllTrips() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("         Lista de Destinos");
        System.out.println("=================================");

        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            System.out.println("ID: " + trip.getTripID() + " - Destino: " + trip.getDestination());
        }

        System.out.println("=================================");
        pause();
    }

    private void selectTrip() {
        System.out.print("Informe o ID do Destino: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Destino não encontrado.");
            pause();
        } else {
            editOrRemoveTripMenu(tripNode.getInfo());
        }
    }

    private void editOrRemoveTripMenu(ItemTrip trip) {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("      Gerenciar Destino");
            System.out.println("=================================");
            System.out.println();
            System.out.println("ID: " + trip.getTripID());
            System.out.println("Destino: " + trip.getDestination());
            System.out.println("Data: " + trip.getDate());
            System.out.println();
            System.out.println("=================================");
            System.out.println("1. Editar Destino");
            System.out.println("2. Remover Destino");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    editTrip(trip);
                    return;
                case 2:
                    removeTrip(trip);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void editTrip(ItemTrip trip) {
        System.out.println("Informe o novo nome do Destino: ");
        System.out.print(">> ");
        String newDestination = scanner.next();
        System.out.println("Informe a nova Data da Viagem: ");
        System.out.print(">> ");
        String newDate = scanner.next();

        if (newDestination.length() > 0) {
            trip.setDestination(newDestination);
        }

        if (newDate.length() > 0) {
            trip.setDate(newDate);
        }

        System.out.println("Destino atualizado com sucesso!");
        pause();
    }

    private void removeTrip(ItemTrip trip) {
        if (tripTree.remove(trip.getTripID())) {
            System.out.println("Destino removido com sucesso!");
        } else {
            System.out.println("Falha ao remover o destino.");
        }
        pause();
    }

    private void pause() {
        System.out.println("Pressione qualquer tecla para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }

}
