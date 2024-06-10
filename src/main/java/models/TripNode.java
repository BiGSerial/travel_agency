package main.java.models;

public class TripNode {
    private int id;
    private String destination;
    private TripNode left, right;

    public TripNode(int id, String destination) {
        this.id = id;
        this.destination = destination;
        this.left = this.right = null;
    }

    public int getId() {
        return id;
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
}