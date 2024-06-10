package main.java.models;

import main.java.trees.TravelerTree;

public class TripNode {
    private int id;
    private String destination;
    private TripNode left;
    private TripNode right;
    private TravelerTree travelers;

    public TripNode(int id, String destination) {
        this.id = id;
        this.destination = destination;
        this.left = null;
        this.right = null;
        this.travelers = new TravelerTree();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public TripNode getLeft() {
        return left;
    }

    public void setLeft(TripNode left) {
        this.left = left;
    }

    public TripNode getRight() {
        return right;
    }

    public void setRight(TripNode right) {
        this.right = right;
    }

    public TravelerTree getTravelers() {
        return travelers;
    }

    public void setTravelers(TravelerTree travelers) {
        this.travelers = travelers;
    }
}