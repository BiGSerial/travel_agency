package main.java.tools;

import java.util.Random;

import main.java.trees.TravelerTree;
import main.java.trees.TripTree;

public class IDGenerator {

    private static final Random random = new Random();

    public static int generateTravelID(TravelerTree tree) {
        int id;

        if (tree.isEmpty()) {
            return 100;
        }

        do {
            id = random.nextInt(Integer.MAX_VALUE);
            
        } while (tree.searchNode(id) != id); // Verifica se o ID já existe na árvore

        return id;
    }

    public static int generateTripID(TripTree tree) {
        int id;

        if (tree.isEmpty()) {
            return 100;
        }

        do {
            id = random.nextInt(Integer.MAX_VALUE);
        } while (tree.searcNode(id)); // Verifica se o ID já existe na árvore

        return id;
    }

}
