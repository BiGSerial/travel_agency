package main.java.models;

import main.java.trees.TravelerTree;

public class TravelerNode {
    private int id;
    private String name;
    private TravelerNode left;
    private TravelerNode right;
    private TravelerTree dependents;
    private static int idCounter = 1;

    public TravelerNode(String name) {
        this.id = idCounter++;
        this.name = name;
        this.left = null;
        this.right = null;
        this.dependents = new TravelerTree();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public TravelerNode getLeft() {
        return left;
    }

    public void setLeft(TravelerNode left) {
        this.left = left;
    }

    public TravelerNode getRight() {
        return right;
    }

    public void setRight(TravelerNode right) {
        this.right = right;
    }

    public TravelerTree getDependents() {
        return dependents;
    }

    public void setDependents(TravelerTree dependents) {
        this.dependents = dependents;
    }
}