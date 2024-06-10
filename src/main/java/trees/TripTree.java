package main.java.trees;

import main.java.models.TripNode;

public class TripTree {
    private TripNode root;
    private int idCounter; // Terá uma dupla função, além de informar a quantidade de nós, informará sempre
                           // o ultimo nó.

    public TripTree() {
        this.root = null;
        this.idCounter = 0;
    }

    public void inserir(String destination) {
        int id = ++idCounter;
        this.root = inserirArvore(this.root, id, destination);
    }

    private TripNode inserirArvore(TripNode root, int id, String destination) {
        if (root == null) {
            root = new TripNode(id, destination);

            return root;
        }

        if (id < root.getId()) {
            root.setLeft(inserirArvore(root.getLeft(), id, destination));
        } else if (id > root.getId()) {
            root.setRight(inserirArvore(root.getRight(), id, destination));
        }

        return root;
    }

    public TripNode buscar(int id) {
        return buscarArvore(this.root, id);
    }

    private TripNode buscarArvore(TripNode root, int id) {
        if (root == null || root.getId() == id) {
            return root;
        }

        if (root.getId() > id) {
            return buscarArvore(root.getLeft(), id);
        }

        return buscarArvore(root.getRight(), id);
    }
}
