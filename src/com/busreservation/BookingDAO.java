package com.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BookingDAO {

    public void bookTicket(String name, String phone,
                           int busId, int seats) {

        Connection con = null;

        try {

            con = (Connection) DBConnection.getConnection();

            String checkSql =
                    "SELECT available_seats, fare FROM buses WHERE bus_id = ?";

            PreparedStatement check =
                    con.prepareStatement(checkSql);

            check.setInt(1, busId);

            ResultSet rs = check.executeQuery();

            if (!rs.next()) {

                System.out.println("Bus not found.");
                return;
            }

            int availableSeats =
                    rs.getInt("available_seats");

            double fare =
                    rs.getDouble("fare");

            if (availableSeats < seats) {

                System.out.println(
                        "Only " + availableSeats +
                        " seats are available.");

                return;
            }

            double totalFare = fare * seats;

            String bookingSql =
                    "INSERT INTO bookings " +
                    "(passenger_name, phone, bus_id, seats_booked, total_fare) " +
                    "VALUES (?, ?, ?, ?, ?)";

            PreparedStatement booking =
                    con.prepareStatement(bookingSql);

            booking.setString(1, name);
            booking.setString(2, phone);
            booking.setInt(3, busId);
            booking.setInt(4, seats);
            booking.setDouble(5, totalFare);

            booking.executeUpdate();

            String updateSql =
                    "UPDATE buses SET available_seats = " +
                    "available_seats - ? WHERE bus_id = ?";

            PreparedStatement update =
                    con.prepareStatement(updateSql);

            update.setInt(1, seats);
            update.setInt(2, busId);

            update.executeUpdate();

            System.out.println("\n================================");
            System.out.println("       TICKET BOOKED");
            System.out.println("================================");

            System.out.println("Passenger Name : " + name);
            System.out.println("Phone          : " + phone);
            System.out.println("Bus ID         : " + busId);
            System.out.println("Seats Booked   : " + seats);
            System.out.println("Total Fare     : ₹" + totalFare);

            System.out.println("================================");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void viewBookings() {

        String sql = "SELECT * FROM bookings";

        try (Connection con = (Connection) DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {

                found = true;

                System.out.println("--------------------------------");
                System.out.println(
                        "Booking ID     : " +
                        rs.getInt("booking_id"));

                System.out.println(
                        "Passenger Name  : " +
                        rs.getString("passenger_name"));

                System.out.println(
                        "Phone           : " +
                        rs.getString("phone"));

                System.out.println(
                        "Bus ID          : " +
                        rs.getInt("bus_id"));

                System.out.println(
                        "Seats Booked    : " +
                        rs.getInt("seats_booked"));

                System.out.println(
                        "Total Fare      : ₹" +
                        rs.getDouble("total_fare"));

                System.out.println(
                        "Booking Date    : " +
                        rs.getTimestamp("booking_date"));
            }

            if (!found) {
                System.out.println("No bookings available.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void cancelTicket(int bookingId) {

        Connection con = null;

        try {

            con = (Connection) DBConnection.getConnection();

            String findSql =
                    "SELECT bus_id, seats_booked " +
                    "FROM bookings WHERE booking_id = ?";

            PreparedStatement find =
                    con.prepareStatement(findSql);

            find.setInt(1, bookingId);

            ResultSet rs = find.executeQuery();

            if (!rs.next()) {

                System.out.println("Booking not found.");
                return;
            }

            int busId = rs.getInt("bus_id");
            int seats = rs.getInt("seats_booked");

            String deleteSql =
                    "DELETE FROM bookings WHERE booking_id = ?";

            PreparedStatement delete =
                    con.prepareStatement(deleteSql);

            delete.setInt(1, bookingId);
            delete.executeUpdate();

            String updateSql =
                    "UPDATE buses SET available_seats = " +
                    "available_seats + ? WHERE bus_id = ?";

            PreparedStatement update =
                    con.prepareStatement(updateSql);

            update.setInt(1, seats);
            update.setInt(2, busId);

            update.executeUpdate();

            System.out.println(
                    "Ticket cancelled successfully!");

        } catch (Exception e) {

            e.printStackTrace();

        } finally {

            try {
                if (con != null)
                    con.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}


