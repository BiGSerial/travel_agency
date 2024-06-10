package main.java.trees;

import main.java.models.TravelerNode;
import main.java.models.TripNode;

public class TripTree {
    private TripNode root;
    private int nextId = 1;

    public void insert(String destination) {
        TripNode newNode = new TripNode(nextId++, destination);
        root = insertRec(root, newNode);
    }

    private TripNode insertRec(TripNode root, TripNode newNode) {
        if (root == null) {
            root = newNode;
            return root;
        }
        if (newNode.getDestination().compareTo(root.getDestination()) < 0) {
            root.setLeft(insertRec(root.getLeft(), newNode));
        } else if (newNode.getDestination().compareTo(root.getDestination()) > 0) {
            root.setRight(insertRec(root.getRight(), newNode));
        }
        return root;
    }

    public TripNode searchByDestination(String destination) {
        return searchByDestinationRec(root, destination);
    }

    private TripNode searchByDestinationRec(TripNode root, String destination) {
        if (root == null || root.getDestination().equals(destination)) {
            return root;
        }
        if (root.getDestination().compareTo(destination) > 0) {
            return searchByDestinationRec(root.getLeft(), destination);
        }
        return searchByDestinationRec(root.getRight(), destination);
    }

    public TripNode searchById(int id) {
        return searchByIdRec(root, id);
    }

    private TripNode searchByIdRec(TripNode root, int id) {
        if (root == null || root.getId() == id) {
            return root;
        }
        TripNode leftResult = searchByIdRec(root.getLeft(), id);
        if (leftResult != null) {
            return leftResult;
        }
        return searchByIdRec(root.getRight(), id);
    }

    public void removeById(int id) {
        root = removeRec(root, id);
    }

    private TripNode removeRec(TripNode root, int id) {
        if (root == null) {
            return root;
        }
        if (id < root.getId()) {
            root.setLeft(removeRec(root.getLeft(), id));
        } else if (id > root.getId()) {
            root.setRight(removeRec(root.getRight(), id));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            root.setId(minValue(root.getRight()));
            root.setRight(removeRec(root.getRight(), root.getId()));
        }
        return root;
    }

    private int minValue(TripNode root) {
        int minValue = root.getId();
        while (root.getLeft() != null) {
            minValue = root.getLeft().getId();
            root = root.getLeft();
        }
        return minValue;
    }

    public void addTraveler(int tripId, TravelerNode traveler) {
        TripNode trip = searchById(tripId);
        if (trip != null) {
            trip.getTravelers().insert(traveler);
        }
    }

    public void removeTraveler(int tripId, int travelerId) {
        TripNode trip = searchById(tripId);
        if (trip != null) {
            trip.getTravelers().removeById(travelerId);
        }
    }

    public void transferTraveler(int fromTripId, int toTripId, TravelerNode traveler) {
        TripNode fromTrip = searchById(fromTripId);
        TripNode toTrip = searchById(toTripId);
        if (fromTrip != null && toTrip != null) {
            removeTraveler(fromTripId, traveler.getId());
            addTraveler(toTripId, traveler);
        }
    }
}
