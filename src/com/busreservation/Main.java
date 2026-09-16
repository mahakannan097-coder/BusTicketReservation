// Jenkins automatic build test

package com.busreservation;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        BusDAO busDAO = new BusDAO();
        BookingDAO bookingDAO = new BookingDAO();

        int choice;

        do {

            System.out.println("\n======================================");
            System.out.println("       BUS TICKET RESERVATION");
            System.out.println("======================================");

            System.out.println("1. View All Buses");
            System.out.println("2. Search Bus");
            System.out.println("3. Book Ticket");
            System.out.println("4. View Bookings");
            System.out.println("5. Cancel Ticket");
            System.out.println("6. Exit");

            System.out.println("======================================");

            System.out.print("Enter your choice: ");
            choice = sc.nextInt();

            sc.nextLine();

            switch (choice) {

                case 1:

                    busDAO.viewAllBuses();

                    break;

                case 2:

                    System.out.print("Enter source: ");
                    String source = sc.nextLine();

                    System.out.print("Enter destination: ");
                    String destination = sc.nextLine();

                    busDAO.searchBus(source, destination);

                    break;

                case 3:

                    System.out.print("Enter passenger name: ");
                    String name = sc.nextLine();

                    System.out.print("Enter phone number: ");
                    String phone = sc.nextLine();

                    System.out.print("Enter Bus ID: ");
                    int busId = sc.nextInt();

                    System.out.print("Enter number of seats: ");
                    int seats = sc.nextInt();

                    sc.nextLine();

                    bookingDAO.bookTicket(
                            name,
                            phone,
                            busId,
                            seats
                    );

                    break;

                case 4:

                    bookingDAO.viewBookings();

                    break;

                case 5:

                    System.out.print(
                            "Enter Booking ID to cancel: ");

                    int bookingId = sc.nextInt();

                    bookingDAO.cancelTicket(bookingId);

                    break;

                case 6:

                    System.out.println(
                            "Thank you for using Bus Ticket Reservation System!");

                    break;

                default:

                    System.out.println(
                            "Invalid choice. Try again.");
            }

        } while (choice != 6);

        sc.close();
    }
}


