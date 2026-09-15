package com.busreservation;

public class Bus {

    private int busId;
    private String busName;
    private String source;
    private String destination;
    private String travelDate;
    private int totalSeats;
    private int availableSeats;
    private double fare;

    public Bus(int busId, String busName, String source,
               String destination, String travelDate,
               int totalSeats, int availableSeats, double fare) {

        this.busId = busId;
        this.busName = busName;
        this.source = source;
        this.destination = destination;
        this.travelDate = travelDate;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
        this.fare = fare;
    }

    public int getBusId() {
        return busId;
    }

    public String getBusName() {
        return busName;
    }

    public String getSource() {
        return source;
    }

    public String getDestination() {
        return destination;
    }

    public String getTravelDate() {
        return travelDate;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public double getFare() {
        return fare;
    }

    public void displayBus() {

        System.out.println("--------------------------------------------");

        System.out.println("Bus ID          : " + busId);
        System.out.println("Bus Name        : " + busName);
        System.out.println("From            : " + source);
        System.out.println("To              : " + destination);
        System.out.println("Travel Date     : " + travelDate);
        System.out.println("Available Seats : " + availableSeats);
        System.out.println("Fare            : ₹" + fare);

        System.out.println("--------------------------------------------");
    }
}


