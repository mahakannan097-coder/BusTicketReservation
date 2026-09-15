
	package com.busreservation;

	public class Booking {

	    private int bookingId;
	    private String passengerName;
	    private String phone;
	    private int busId;
	    private int seatsBooked;
	    private double totalFare;

	    public Booking(int bookingId, String passengerName,
	                   String phone, int busId,
	                   int seatsBooked, double totalFare) {

	        this.bookingId = bookingId;
	        this.passengerName = passengerName;
	        this.phone = phone;
	        this.busId = busId;
	        this.seatsBooked = seatsBooked;
	        this.totalFare = totalFare;
	    }

	    public int getBookingId() {
	        return bookingId;
	    }

	    public String getPassengerName() {
	        return passengerName;
	    }

	    public String getPhone() {
	        return phone;
	    }

	    public int getBusId() {
	        return busId;
	    }

	    public int getSeatsBooked() {
	        return seatsBooked;
	    }

	    public double getTotalFare() {
	        return totalFare;
	    }
	}

