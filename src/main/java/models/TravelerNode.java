package main.java.models;

import main.java.data.ItemTraveler;
import main.java.trees.TravelerTree;

public class TravelerNode {
    
    private ItemTraveler info;
    private TravelerNode left;
    private TravelerNode right;
    
   

    public TravelerNode(ItemTraveler traveler) {
        this.info = traveler;       
        this.left = null;
        this.right = null;
        // this.dependents = new TravelerTree();
    }

    public ItemTraveler getInfo() {
        return this.info;
    }

    public void setInfo(ItemTraveler info) {
        this.info = info;
    }

    public TravelerNode getLeft() {
        return this.left;
    }

    public void setLeft(TravelerNode left) {
        this.left = left;
    }

    public TravelerNode getRight() {
        return this.right;
    }

    public void setRight(TravelerNode right) {
        this.right = right;
    }


    
}