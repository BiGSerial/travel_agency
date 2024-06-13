package test.java;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import main.java.models.TripNode;
import main.java.models.TravelerNode;
import main.java.trees.TripTree;

public class TripTreeTest {
    private TripTree tripTree;

    @BeforeEach
    public void setUp() {
        tripTree = new TripTree();
    }

    @Test
    public void testInsertAndSearchByDestination() {
        tripTree.insert("Paris");
        TripNode trip = tripTree.searchByDestination("Paris");
        assertNotNull(trip);
        assertEquals("Paris", trip.getDestination());
    }

    @Test
    public void testInsertAndSearchById() {
        tripTree.insert("Paris");
        TripNode trip = tripTree.searchByDestination("Paris");
        TripNode tripById = tripTree.searchById(trip.getId());
        assertNotNull(tripById);
        assertEquals(trip.getId(), tripById.getId());
    }

    @Test
    public void testRemoveById() {
        tripTree.insert("Paris");
        TripNode trip = tripTree.searchByDestination("Paris");
        tripTree.removeById(trip.getId());
        TripNode removedTrip = tripTree.searchById(trip.getId());
        assertNull(removedTrip);
    }

    @Test
    public void testAddTraveler() {
        tripTree.insert("Paris");
        TripNode trip = tripTree.searchByDestination("Paris");
        TravelerNode traveler = new TravelerNode("Joao");
        tripTree.addTraveler(trip.getId(), traveler);
        assertNotNull(trip.getTravelers().searchById(traveler.getId()));
    }

    @Test
    public void testRemoveTraveler() {
        tripTree.insert("Paris");
        TripNode trip = tripTree.searchByDestination("Paris");
        TravelerNode traveler = new TravelerNode("Joao");
        tripTree.addTraveler(trip.getId(), traveler);
        tripTree.removeTraveler(trip.getId(), traveler.getId());
        assertNull(trip.getTravelers().searchById(traveler.getId()));
    }

    @Test
    public void testTransferTraveler() {
        tripTree.insert("Paris");
        tripTree.insert("New York");
        TripNode parisTrip = tripTree.searchByDestination("Paris");
        TripNode newYorkTrip = tripTree.searchByDestination("New York");
        TravelerNode traveler = new TravelerNode("Joao");
        tripTree.addTraveler(parisTrip.getId(), traveler);
        tripTree.transferTraveler(parisTrip.getId(), newYorkTrip.getId(), traveler);
        assertNull(parisTrip.getTravelers().searchById(traveler.getId()));
        assertNotNull(newYorkTrip.getTravelers().searchById(traveler.getId()));
    }
}