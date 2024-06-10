package main.java.models;

public class TravelerNode {
    private int id;
    private String name;
    private TravelerNode left, right;

    public TravelerNode(int id, String name) {
        this.id = id;
        this.name = name;
        this.left = this.right = null;
    }

    public int getId() {
        return id;
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
}