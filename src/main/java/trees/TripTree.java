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
        if (searchNode(trip.getId()) != null) {
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
            if (trip.getId() < node.getInfo().getId()) {
                node.setLeft(insert(trip, node.getLeft()));
                return node;
            } else {
                node.setRight(insert(trip, node.getRight()));
                return node;
            }
        }
    }

    public TripNode searchNode(int id) {
        return searchNode(id, this.root);
    }

    private TripNode searchNode(int id, TripNode node) {
        if (node == null) {
            return null;
        }
        if (id < node.getInfo().getId()) {
            return searchNode(id, node.getLeft());
        } else if (id > node.getInfo().getId()) {
            return searchNode(id, node.getRight());
        } else {
            return node;
        }
    }

    public boolean remove(int id) {
        if (searchNode(id) != null) {
            this.root = remove(id, this.root);
            this.countNode--;
            return true;
        } else {
            return false;
        }
    }

    private TripNode remove(int id, TripNode node) {
        if (id < node.getInfo().getId()) {
            node.setLeft(remove(id, node.getLeft()));
        } else if (id > node.getInfo().getId()) {
            node.setRight(remove(id, node.getRight()));
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
        ItemTrip[] items = new ItemTrip[this.countNode];
        int[] index = new int[1];
        inOrder(this.root, items, index);
        return items;
    }

    private void inOrder(TripNode node, ItemTrip[] items, int[] index) {
        if (node != null) {
            inOrder(node.getLeft(), items, index);
            items[index[0]++] = node.getInfo();
            inOrder(node.getRight(), items, index);
        }
    }
}
