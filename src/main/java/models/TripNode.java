package main.java.models;

import main.java.data.ItemTrip;

public class TripNode {

    private ItemTrip info;
    private TripNode left;
    private TripNode right;

    public TripNode(ItemTrip trip) {
        this.info = trip;
        this.left = null;
        this.right = null;
    }

    public ItemTrip getInfo() {
        return this.info;
    }

    public void setInfo(ItemTrip info) {
        this.info = info;
    }

    public TripNode getLeft() {
        return this.left;
    }

    public void setLeft(TripNode left) {
        this.left = left;
    }

    public TripNode getRight() {
        return this.right;
    }

    public void setRight(TripNode right) {
        this.right = right;
    }

}