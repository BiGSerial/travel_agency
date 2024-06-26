package main.java.data;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import main.java.models.TravelerNode;
import main.java.models.TripNode;
import main.java.trees.TravelerTree;

public class ItemTrip {
    private int tripId;
    private String date;
    private String destination;
    private List<Integer> travelerIds;

    public ItemTrip(int tripId, String date, String destination) {
        travelerIds = new ArrayList<>();
        this.tripId = tripId;
        this.date = date;
        this.destination = destination;
    }

    public int getTripID() {
        return this.tripId;
    }

    public void setTripID(int id) {
        this.tripId = id;
    }

    public String getDate() {
        return this.date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getDestination() {
        return this.destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public List<Integer> getTravelerIds() {
        return travelerIds;
    }

    public boolean addTravelerId(int travelerId) {
        Set<Integer> uniqueIds = new HashSet<>(travelerIds);
        if (uniqueIds.add(travelerId)) {
            travelerIds.add(travelerId);
            return true;
        }
        return false;
    }

    public boolean removeTravelerId(int travelerId) {
        return travelerIds.remove(Integer.valueOf(travelerId));
    }

    public void removeTravelerById(int travelerId) {
        travelerIds.remove(Integer.valueOf(travelerId));
    }

    public boolean existsTravelerId(int travelerId) {
        return travelerIds.contains(travelerId);
    }

    public int getTotalTravelers() {
        return travelerIds.size();
    }

}
