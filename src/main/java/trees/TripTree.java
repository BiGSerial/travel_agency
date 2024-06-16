package main.java.trees;

import main.java.data.ItemTrip;
import main.java.models.TripNode;

public class TripTree {
    private TripNode root;
    private int countNode;

    public TripTree() {
        this.countNode = 0;
        this.root = null;
    }

    public boolean isEmpty() {
        return (this.root == null);
    }

    public TripNode getRoot() {
        return this.root;
    }

    public int getCountNode() {
        return this.countNode;
    }

    public boolean insert(ItemTrip trip) {
        if (searchNode(trip.getTripID()) != null) {
            return false;
        } else {
            this.root = insert(trip, this.root);
            this.countNode++;
            return true;
        }
    }

    private TripNode insert(ItemTrip trip, TripNode node) {
        if (node == null) {
            TripNode newNode = new TripNode(trip);
            return newNode;
        } else {
            if (trip.getTripID() < node.getInfo().getTripID()) {
                node.setLeft(insert(trip, node.getLeft()));
                return node;
            } else {
                node.setRight(insert(trip, node.getRight()));
                return node;
            }
        }
    }

    public TripNode searchNode(int idTrip) {
        return searchNode(idTrip, this.root);
    }

    private TripNode searchNode(int idTrip, TripNode node) {
        if (node == null) {
            return null;
        }
        if (idTrip < node.getInfo().getTripID()) {
            return searchNode(idTrip, node.getLeft());
        } else if (idTrip > node.getInfo().getTripID()) {
            return searchNode(idTrip, node.getRight());
        } else {
            return node;
        }
    }

    public boolean remove(int tripId) {
        if (searchNode(tripId) != null) {
            this.root = remove(tripId, this.root);
            this.countNode--;
            return true;
        } else {
            return false;
        }
    }

    private TripNode remove(int tripId, TripNode node) {
        if (tripId < node.getInfo().getTripID()) {
            node.setLeft(remove(tripId, node.getLeft()));
        } else if (tripId > node.getInfo().getTripID()) {
            node.setRight(remove(tripId, node.getRight()));
        } else {
            if (node.getRight() == null) {
                return node.getLeft();
            } else if (node.getLeft() == null) {
                return node.getRight();
            } else {
                node.setLeft(fixTree(node, node.getLeft()));
            }
        }
        return node;
    }

    private TripNode fixTree(TripNode node, TripNode nodeHigh) {
        if (nodeHigh.getRight() != null) {
            nodeHigh.setRight(fixTree(node, nodeHigh.getRight()));
        } else {
            node.setInfo(nodeHigh.getInfo());
            nodeHigh = nodeHigh.getLeft();
        }
        return nodeHigh;
    }

    public ItemTrip[] inOrder() {
        int[] n = new int[1];
        n[0] = 0;
        ItemTrip[] vet = new ItemTrip[this.countNode];
        return (doInOrder(this.root, vet, n));
    }

    private ItemTrip[] doInOrder(TripNode node, ItemTrip[] vet, int[] n) {
        if (node != null) {
            vet = doInOrder(node.getLeft(), vet, n);
            vet[n[0]] = node.getInfo();
            n[0]++;
            vet = doInOrder(node.getRight(), vet, n);
        }
        return vet;
    }

    public ItemTrip[] preOrder() {
        int[] n = new int[1];
        n[0] = 0;
        ItemTrip[] vet = new ItemTrip[this.countNode];
        return (doPreOrder(this.root, vet, n));
    }

    private ItemTrip[] doPreOrder(TripNode node, ItemTrip[] vet, int[] n) {
        if (node != null) {
            vet[n[0]] = node.getInfo();
            n[0]++;
            vet = doPreOrder(node.getLeft(), vet, n);
            vet = doPreOrder(node.getRight(), vet, n);
        }
        return vet;
    }

    public ItemTrip[] postOrder() {
        int[] n = new int[1];
        n[0] = 0;
        ItemTrip[] vet = new ItemTrip[this.countNode];
        return (doPostOrder(this.root, vet, n));
    }

    private ItemTrip[] doPostOrder(TripNode node, ItemTrip[] vet, int[] n) {
        if (node != null) {
            vet = doPostOrder(node.getLeft(), vet, n);
            vet = doPostOrder(node.getRight(), vet, n);
            vet[n[0]] = node.getInfo();
            n[0]++;
        }
        return vet;
    }
}
