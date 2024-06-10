package main.java.associates;

public class AssociationNode {
    private int tripId;
    private int travelerId;
    private AssociationNode prev, next;

    public AssociationNode(int tripId, int travelerId) {
        this.tripId = tripId;
        this.travelerId = travelerId;
        this.prev = this.next = null;
    }

    public int getTripId() {
        return tripId;
    }

    public int getTravelerId() {
        return travelerId;
    }

    public AssociationNode getPrev() {
        return prev;
    }

    public void setPrev(AssociationNode prev) {
        this.prev = prev;
    }

    public AssociationNode getNext() {
        return next;
    }

    public void setNext(AssociationNode next) {
        this.next = next;
    }
}
