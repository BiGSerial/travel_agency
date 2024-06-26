package main.java.tools;

import java.util.Random;
import main.java.trees.TravelerTree;
import main.java.trees.TripTree;

public class IDGenerator {

    private static final Random random = new Random();

    public static int generateTravelID(TravelerTree tree) {
        int id;

        if (tree.isEmpty()) {
            return 250;
        }

        do {
            id = random.nextInt(500);
        } while (tree.searchNode(id) != null); // Verifica se o ID já existe na árvore

        return id;
    }

    public static int generateTripID(TripTree tree) {
        int id;

        if (tree.isEmpty()) {
            return 250;
        }

        do {
            id = random.nextInt(500);
        } while (tree.searchNode(id) != null); // Verifica se o ID já existe na árvore

        return id;
    }
}
