package main.java.data;

import java.util.ArrayList;
import java.util.List;

public class ItemTrip {
    private int tripId;
    private String date;
    private String destination;
    private List<ItemTraveler> travelers;

    public ItemTrip(int tripId, String date, String destination) {
        travelers = new ArrayList<>();
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

    public List<ItemTraveler> getTravelers() {
        return travelers;
    }

    public void addTraveler(ItemTraveler traveler) {
        travelers.add(traveler);
    }

    public void removeTraveler(ItemTraveler traveler) {
        travelers.remove(traveler);
    }

    public void removeTravelerById(int travelerId) {
        travelers.removeIf(traveler -> traveler.getTravelerId() == travelerId);
    }
}
