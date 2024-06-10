package main.java.trees;

import main.java.models.TravelerNode;

public class TravelerTree {

    private TravelerNode root;

    public TravelerTree() {
        this.root = null;
    }

    public void insert(TravelerNode traveler) {
        root = insertRec(root, traveler);
    }

    private TravelerNode insertRec(TravelerNode root, TravelerNode traveler) {
        if (root == null) {
            root = traveler;
            return root;
        }
        if (traveler.getName().compareTo(root.getName()) < 0) {
            root.setLeft(insertRec(root.getLeft(), traveler));
        } else if (traveler.getName().compareTo(root.getName()) > 0) {
            root.setRight(insertRec(root.getRight(), traveler));
        }
        return root;
    }

    public TravelerNode searchById(int id) {
        return searchByIdRec(root, id);
    }

    private TravelerNode searchByIdRec(TravelerNode root, int id) {
        if (root == null || root.getId() == id) {
            return root;
        }
        if (id < root.getId()) {
            return searchByIdRec(root.getLeft(), id);
        }
        return searchByIdRec(root.getRight(), id);
    }

    public TravelerNode searchByName(String name) {
        return searchByNameRec(root, name);
    }

    private TravelerNode searchByNameRec(TravelerNode root, String name) {
        if (root == null || root.getName().equals(name)) {
            return root;
        }
        if (name.compareTo(root.getName()) < 0) {
            return searchByNameRec(root.getLeft(), name);
        }
        return searchByNameRec(root.getRight(), name);
    }

    public void removeById(int id) {
        root = removeByIdRec(root, id);
    }

    private TravelerNode removeByIdRec(TravelerNode root, int id) {
        if (root == null) {
            return root;
        }
        if (id < root.getId()) {
            root.setLeft(removeByIdRec(root.getLeft(), id));
        } else if (id > root.getId()) {
            root.setRight(removeByIdRec(root.getRight(), id));
        } else {
            if (root.getLeft() == null) {
                return root.getRight();
            } else if (root.getRight() == null) {
                return root.getLeft();
            }
            root.setName(findMin(root.getRight()).getName());
            root.setRight(removeByIdRec(root.getRight(), root.getId()));
        }
        return root;
    }

    private TravelerNode findMin(TravelerNode root) {
        while (root.getLeft() != null) {
            root = root.getLeft();
        }
        return root;
    }

    public void addDependent(int travelerId, TravelerNode dependent) {
        TravelerNode traveler = searchById(travelerId);
        if (traveler != null) {
            traveler.getDependents().insert(dependent);
        }
    }

    public void removeDependent(int travelerId, int dependentId) {
        TravelerNode traveler = searchById(travelerId);
        if (traveler != null) {
            traveler.getDependents().removeById(dependentId);
        }
    }

    public void transferDependent(int fromTravelerId, int toTravelerId, TravelerNode dependent) {
        removeDependent(fromTravelerId, dependent.getId());
        addDependent(toTravelerId, dependent);
    }
}
