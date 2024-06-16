package main.java.view;

import main.java.data.ItemTraveler;
import main.java.models.TravelerNode;
import main.java.tools.IDGenerator;
import main.java.tools.ScreenTools;
import main.java.trees.TravelerTree;

import java.util.Scanner;

public class TravelerMenu {
    private Scanner scanner = new Scanner(System.in);
    private TravelerTree travelerTree;
    private MainMenu mainMenu;

    public TravelerMenu(TravelerTree travelerTree, MainMenu mainMenu) {
        this.travelerTree = travelerTree;
        this.mainMenu = mainMenu;
    }

    public void displayMenu() {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("        Gerenciar Viajantes");
            System.out.println("=================================");
            System.out.println("1. Cadastrar Viajante");
            System.out.println("2. Buscar Viajante");
            System.out.println("3. Listar Todos os Viajantes (Pré-Ordem)");
            System.out.println("4. Listar Todos os Viajantes (Pós-Ordem)");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

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
                case 0:
                    mainMenu.mainMenu();
                    break;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void newTraveler() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("      Cadastrar Novo Viajante");
        System.out.println("=================================");
        System.out.println();
        System.out.println("Informe o nome do Viajante:");
        System.out.print(">> ");
        String name = scanner.next();
        System.out.println("Informe o sobrenome do Viajante:");
        System.out.print(">> ");
        String lastname = scanner.next();
        System.out.println("Informe a idade do Viajante:");
        System.out.print(">> ");
        int age = scanner.nextInt();

        ItemTraveler traveler = new ItemTraveler();
        traveler.setTravelerId(IDGenerator.generateTravelID(travelerTree));
        traveler.setName(name);
        traveler.setLastname(lastname);
        traveler.setAge(age);

        if (travelerTree.insert(traveler)) {
            System.out.println();
            System.out.println("CADASTRADO COM SUCESSO!");
            System.out.println();
            pause();
            displayMenu();
        } else {
            System.out.println();
            System.out.println("OOPS! Ocorreu algum erro ao cadastrar o viajante");
            System.out.println();
            pause();
            displayMenu();
        }
    }

    private void searchTravelerMenu() {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("        Buscar Viajantes");
            System.out.println("=================================");
            System.out.println("1. Listar Todos os Viajantes");
            System.out.println("2. Selecionar Viajante");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    listAllTravelers();
                    break;
                case 2:
                    selectTraveler();
                    break;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void listAllTravelers() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("        Lista de Viajantes");
        System.out.println("=================================");

        ItemTraveler[] travelers = travelerTree.inOrder();
        for (ItemTraveler traveler : travelers) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                    + traveler.getLastname());
        }

        System.out.println("=================================");
        pause();
    }

    private void listPreOrder() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("    Lista de Viajantes (Pré-Ordem)");
        System.out.println("=================================");

        ItemTraveler[] travelers = travelerTree.preOrder();
        for (ItemTraveler traveler : travelers) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                    + traveler.getLastname());
        }

        System.out.println("=================================");
        pause();
    }

    private void listPostOrder() {
        ScreenTools.programTitle();
        System.out.println("=================================");
        System.out.println("    Lista de Viajantes (Pós-Ordem)");
        System.out.println("=================================");

        ItemTraveler[] travelers = travelerTree.postOrder();
        for (ItemTraveler traveler : travelers) {
            System.out.println("ID: " + traveler.getTravelerId() + " - Nome: " + traveler.getName() + " "
                    + traveler.getLastname());
        }

        System.out.println("=================================");
        pause();
    }

    private void selectTraveler() {
        System.out.print("Informe o ID do Viajante: ");
        int travelerId = scanner.nextInt();
        TravelerNode travelerNode = travelerTree.searchNode(travelerId);

        if (travelerNode == null) {
            System.out.println("Viajante não encontrado.");
            pause();
        } else {
            editOrRemoveTravelerMenu(travelerNode.getInfo());
        }
    }

    private void editOrRemoveTravelerMenu(ItemTraveler traveler) {
        while (true) {
            ScreenTools.programTitle();
            System.out.println("=================================");
            System.out.println("      Gerenciar Viajante");
            System.out.println("=================================");
            System.out.println();
            System.out.println("ID: " + traveler.getTravelerId());
            System.out.println("Nome: " + traveler.getName());
            System.out.println("Sobrenome: " + traveler.getLastname());
            System.out.println("Idade: " + traveler.getAge());
            System.out.println();
            System.out.println("=================================");
            System.out.println("1. Editar Viajante");
            System.out.println("2. Remover Viajante");
            System.out.println("0. Voltar Menu Anterior");
            System.out.println("=================================");
            System.out.print("Selecione uma Opção: ");

            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    editTraveler(traveler);
                    return;
                case 2:
                    removeTraveler(traveler);
                    return;
                case 0:
                    return;
                default:
                    System.out.println("Opção inválida. Por favor, tente novamente.");
            }
        }
    }

    private void editTraveler(ItemTraveler traveler) {
        System.out.println("Informe o novo nome do Viajante: ");
        System.out.print(">> ");
        String newName = scanner.next();
        System.out.println("Informe o novo sobrenome do Viajante: ");
        System.out.print(">> ");
        String newLastname = scanner.next();
        System.out.println("Informe a nova idade do Viajante: ");
        System.out.print(">> ");
        int newAge = scanner.nextInt();

        if (!newName.isEmpty()) {
            traveler.setName(newName);
        }

        if (!newLastname.isEmpty()) {
            traveler.setLastname(newLastname);
        }

        if (newAge > 0) {
            traveler.setAge(newAge);
        }

        System.out.println("Viajante atualizado com sucesso!");
        pause();
    }

    private void removeTraveler(ItemTraveler traveler) {
        if (travelerTree.remove(traveler.getTravelerId())) {
            System.out.println("Viajante removido com sucesso!");
        } else {
            System.out.println("Falha ao remover o viajante.");
        }
        pause();
    }

    private void pause() {
        System.out.println("Pressione qualquer tecla para continuar...");
        scanner.nextLine();
        scanner.nextLine();
    }
}
