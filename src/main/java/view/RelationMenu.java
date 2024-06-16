package main.java.view;

import main.java.data.ItemTraveler;
import main.java.data.ItemTrip;
import main.java.models.TravelerNode;
import main.java.models.TripNode;
import main.java.tools.IDGenerator;
import main.java.tools.ScreenTools;
import main.java.trees.TravelerTree;
import main.java.trees.TripTree;

import java.util.Scanner;

public class RelationMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerTree travelerTree;
    private TripTree tripTree;
    private MainMenu mainMenu;

    public RelationMenu(TravelerTree travelerTree, TripTree tripTree, MainMenu mainMenu) {
        this.travelerTree = travelerTree;
        this.tripTree = tripTree;
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("        Gerenciar Relações");
            System.out.println("=================================");
            System.out.println("1. Listar Viagens");
            System.out.println("2. Listar Viajantes");
            System.out.println("3. Exibir Relatório de Viajantes Únicos");
            System.out.println("0. Voltar Menu Principal");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listTrips();
                    break;
                case 2:
                    listTravelers();
                    break;
                case 3:
                    showUniqueTravelersReport();
                    break;
                case 0:
                    mainMenu.mainMenu();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void listTrips() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("         Lista de Viagens");
        System.out.println("=================================");

        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            System.out.println("ID: " + trip.getTripID() + " - Destino: " + trip.getDestination() + " - Data: "
                    + trip.getDate() + " - Nº de Viajantes: " + trip.getTravelers().size());
        }

        System.out.println("=================================");
        System.out.print("Informe o ID da Viagem para ver detalhes ou 0 para voltar: ");
        int tripId = scanner.nextInt();
        if (tripId != 0) {
            showTripDetails(tripId);
        }
    }

    private void showTripDetails(int tripId) {
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Viagem não encontrada.");
            pause();
            return;
        }

        ItemTrip trip = tripNode.getInfo();
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Detalhes da Viagem");
        System.out.println("=================================");
        System.out.println("ID: " + trip.getTripID());
        System.out.println("Destino: " + trip.getDestination());
        System.out.println("Data: " + trip.getDate());
        System.out.println("Nº de Viajantes: " + trip.getTravelers().size());
        System.out.println();
        System.out.println("Viajantes:");
        for (ItemTraveler traveler : trip.getTravelers()) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                    + traveler.getLastname());
        }
        System.out.println("=================================");
        System.out.println("1. Adicionar Viajante");
        System.out.println("2. Remover Viajante");
        System.out.println("0. Voltar");
        System.out.println("=================================");
        System.out.print("Selecione uma Opção: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addTravelerToTrip(trip);
                break;
            case 2:
                removeTravelerFromTrip(trip);
                break;
            case 0:
                listTrips();
                break;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    }

    private void addTravelerToTrip(ItemTrip trip) {
        System.out.print("Informe o ID do Viajante a ser adicionado: ");
        int travelerId = scanner.nextInt();
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Viajante não encontrado.");
            pause();
            return;
        }

        ItemTraveler traveler = travelerNode.getInfo();
        if (!trip.getTravelers().contains(traveler)) {
            trip.addTraveler(traveler);
            System.out.println("Viajante adicionado com sucesso!");
        } else {
            System.out.println("O viajante já está relacionado a essa viagem.");
        }

        pause();
        showTripDetails(trip.getTripID());
    }

    private void removeTravelerFromTrip(ItemTrip trip) {
        System.out.print("Informe o ID do Viajante a ser removido: ");
        int travelerId = scanner.nextInt();

        trip.removeTravelerById(travelerId);
        System.out.println("Viajante removido com sucesso!");

        pause();
        showTripDetails(trip.getTripID());
    }

    private void listTravelers() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("        Lista de Viajantes");
        System.out.println("=================================");

        ItemTraveler[] travelers = travelerTree.inOrder();
        for (ItemTraveler traveler : travelers) {
            int tripCount = getTripCountForTraveler(traveler.getTravelerId());
            System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                    + traveler.getLastname() + " - Nº de Viagens: " + tripCount);
        }

        System.out.println("=================================");
        System.out.print("Informe o ID do Viajante para ver detalhes ou 0 para voltar: ");
        int travelerId = scanner.nextInt();
        if (travelerId != 0) {
            showTravelerDetails(travelerId);
        }
    }

    private int getTripCountForTraveler(int travelerId) {
        int count = 0;
        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            for (ItemTraveler traveler : trip.getTravelers()) {
                if (traveler.getTravelerId() == travelerId) {
                    count++;
                }
            }
        }
        return count;
    }

    private void showTravelerDetails(int travelerId) {
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Viajante não encontrado.");
            pause();
            return;
        }

        ItemTraveler traveler = travelerNode.getInfo();
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Detalhes do Viajante");
        System.out.println("=================================");
        System.out.println("ID: " + traveler.getTravelerId());
        System.out.println("Nome: " + traveler.getName());
        System.out.println("Sobrenome: " + traveler.getLastname());
        System.out.println("Idade: " + traveler.getAge());
        System.out.println();
        System.out.println("Viagens:");
        ItemTrip[] trips = tripTree.inOrder();
        for (ItemTrip trip : trips) {
            for (ItemTraveler tripTraveler : trip.getTravelers()) {
                if (tripTraveler.getTravelerId() == travelerId) {
                    System.out.println("ID da Viagem: " + trip.getTripID() + " - Destino: " + trip.getDestination()
                            + " - Data: " + trip.getDate());
                }
            }
        }

        System.out.println("=================================");
        System.out.println("1. Adicionar Viagem");
        System.out.println("2. Remover Viagem");
        System.out.println("0. Voltar");
        System.out.println("=================================");
        System.out.print("Selecione uma Opção: ");

        int choice = scanner.nextInt();
        switch (choice) {
            case 1:
                addTripToTraveler(traveler);
                break;
            case 2:
                removeTripFromTraveler(traveler);
                break;
            case 0:
                listTravelers();
                break;
            default:
                System.out.println("Opção inválida. Por favor, tente novamente.");
        }
    }

    private void addTripToTraveler(ItemTraveler traveler) {
        System.out.print("Informe o ID da Viagem a ser adicionada: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Viagem não encontrada.");
            pause();
            return;
        }

        ItemTrip trip = tripNode.getInfo();
        if (!trip.getTravelers().contains(traveler)) {
            trip.addTraveler(traveler);
            System.out.println("Viagem adicionada com sucesso!");
        } else {
            System.out.println("O viajante já está relacionado a essa viagem.");
        }

        pause();
        showTravelerDetails(traveler.getTravelerId());
    }

    private void removeTripFromTraveler(ItemTraveler traveler) {
        System.out.print("Informe o ID da Viagem a ser removida: ");
        int tripId = scanner.nextInt();
        TripNode tripNode = tripTree.searchNode(tripId);

        if (tripNode == null) {
            System.out.println("Viagem não encontrada.");
            pause();
            return;
        }

        ItemTrip trip = tripNode.getInfo();
        trip.removeTravelerById(traveler.getTravelerId());
        System.out.println("Viagem removida com sucesso!");

        pause();
        showTravelerDetails(traveler.getTravelerId());
    }

    private void showUniqueTravelersReport() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Relatório de Viajantes Únicos");
        System.out.println("=================================");

        ItemTraveler[] travelers = travelerTree.inOrder();
        for (ItemTraveler traveler : travelers) {
            int tripCount = getTripCountForTraveler(traveler.getTravelerId());
            if (tripCount > 0) {
                System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                        + traveler.getLastname());
                System.out.println("Viagens:");
                ItemTrip[] trips = tripTree.inOrder();
                for (ItemTrip trip : trips) {
                    for (ItemTraveler tripTraveler : trip.getTravelers()) {
                        if (tripTraveler.getTravelerId() == traveler.getTravelerId()) {
                            System.out.println("ID da Viagem: " + trip.getTripID() + " - Destino: "
                                    + trip.getDestination() + " - Data: " + trip.getDate());
                        }
                    }
                }
                System.out.println("---------------------------------");
            }
        }

        System.out.println("=================================");
        pause();
    }

    private void pause() {
        System.out.println("Pressione qualquer tecla para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
