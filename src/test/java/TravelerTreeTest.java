package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.models.TravelerNode;
import main.java.trees.TravelerTree;

public class TravelerTreeTest {
    private TravelerTree travelerTree;

    @BeforeEach
    public void setUp() {
        travelerTree = new TravelerTree();
    }

    @Test
    public void testInsertAndSearchByName() {
        TravelerNode traveler = new TravelerNode("Joao");
        travelerTree.insert(traveler);
        TravelerNode foundTraveler = travelerTree.searchByName("Joao");
        assertNotNull(foundTraveler);
        assertEquals("Joao", foundTraveler.getName());
    }

    @Test
    public void testInsertAndSearchById() {
        TravelerNode traveler = new TravelerNode("Joao");
        travelerTree.insert(traveler);
        TravelerNode foundTraveler = travelerTree.searchById(traveler.getId());
        assertNotNull(foundTraveler);
        assertEquals(traveler.getId(), foundTraveler.getId());
    }

    @Test
    public void testRemoveById() {
        TravelerNode traveler = new TravelerNode("Joao");
        travelerTree.insert(traveler);
        travelerTree.removeById(traveler.getId());
        TravelerNode removedTraveler = travelerTree.searchById(traveler.getId());
        assertNull(removedTraveler);
    }

    @Test
    public void testAddDependent() {
        TravelerNode traveler = new TravelerNode("Joao");
        TravelerNode dependent = new TravelerNode("Joana");
        travelerTree.insert(traveler);
        travelerTree.addDependent(traveler.getId(), dependent);
        assertNotNull(traveler.getDependents().searchById(dependent.getId()));
    }

    @Test
    public void testRemoveDependent() {
        TravelerNode traveler = new TravelerNode("Joao");
        TravelerNode dependent = new TravelerNode("Joana");
        travelerTree.insert(traveler);
        travelerTree.addDependent(traveler.getId(), dependent);
        travelerTree.removeDependent(traveler.getId(), dependent.getId());
        assertNull(traveler.getDependents().searchById(dependent.getId()));
    }

    @Test
    public void testTransferDependent() {
        TravelerNode traveler1 = new TravelerNode("Joao");
        TravelerNode traveler2 = new TravelerNode("Maria");
        TravelerNode dependent = new TravelerNode("Joana");
        travelerTree.insert(traveler1);
        travelerTree.insert(traveler2);
        travelerTree.addDependent(traveler1.getId(), dependent);
        travelerTree.transferDependent(traveler1.getId(), traveler2.getId(), dependent);
        assertNull(traveler1.getDependents().searchById(dependent.getId()));
        assertNotNull(traveler2.getDependents().searchById(dependent.getId()));
    }
}
