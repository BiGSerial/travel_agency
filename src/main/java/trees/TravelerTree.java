package main.java.trees;

import main.java.data.ItemTraveler;
import main.java.models.TravelerNode;

public class TravelerTree {
    private TravelerNode root;
    private int countNode;
    
    public TravelerTree(){
        this.countNode = 0;
        this.root = null;
    }

    public boolean isEmpty(){
        return (this.root == null);
    }

    public TravelerNode getRoot(){
        return this.root;
    }

    public int getCountNode(){
        return this.countNode;
    }

    public boolean insert(ItemTraveler traveler) {
        if (search(traveler.getTravelerId())) {
            return false;
        } else {
            this.root = insert(traveler, this.root);
            this.countNode++;
            return true;
        }
    }

    private TravelerNode insert(ItemTraveler traveler, TravelerNode node) {
        if (node == null) {
            TravelerNode newNode = new TravelerNode(traveler);
            return newNode;
        } else {
            if (traveler.getTravelerId() < node.getInfo().getTravelerId()) {
                node.setLeft(insert(traveler, node.getLeft()));
                return node;
            } else {
                node.setRight(insert(traveler, node.getRight()));
                return node;
            }
        }
    }

    public boolean search(int idTraveler) {
        if (search(idTraveler, this.root) != null) {
            return true;
        } else {
            return false;
        }
    }

    private TravelerNode search(int idTraveler, TravelerNode node) {
        if (node != null) {
            if (idTraveler < node.getInfo().getTravelerId()) {
                node = search(idTraveler, node.getLeft());
            } else {
                if (idTraveler > node.getInfo().getTravelerId()) {
                    node = search(idTraveler, node.getRight());
                }
            }
        }
        return node;
    }

    public boolean remove(int travelerId) {
        if (search(travelerId, this.root) != null) {
            this.root = remove(travelerId, this.root);
            this.countNode--;
            return true;
        } else {
            return false;
        }
    }

    private TravelerNode remove(int travelerId, TravelerNode node) {
        if (travelerId < node.getInfo().getTravelerId()) {
            node.setLeft(remove(travelerId, node.getLeft()));
        } else {
            if (travelerId > node.getInfo().getTravelerId()) {
                node.setRight(remove(travelerId, node.getRight()));
            } else {
                if (node.getRight() == null) {
                    return node.getLeft();
                } else {
                    if (node.getLeft() == null) {
                        return node.getRight();
                    } else {
                        node.setLeft(fixTree(node, node.getLeft()));
                    }
                }
            }
        }
        return node;
    }

    private TravelerNode fixTree(TravelerNode node, TravelerNode nodeHigh) {
        if (nodeHigh.getRight() != null) {
            nodeHigh.setRight(fixTree(node, nodeHigh.getRight()));
        } else {
            node.setInfo(nodeHigh.getInfo());
            nodeHigh = nodeHigh.getLeft();
        }
        return nodeHigh;
    }

}
