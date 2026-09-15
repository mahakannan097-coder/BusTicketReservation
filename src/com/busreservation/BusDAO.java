package com.busreservation;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class BusDAO {

    // ==========================================
    // VIEW ALL BUSES - CONSOLE VERSION
    // ==========================================

    public void viewAllBuses() {

        String sql = "SELECT * FROM buses";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            boolean found = false;

            while (rs.next()) {

                found = true;

                Bus bus = new Bus(
                        rs.getInt("bus_id"),
                        rs.getString("bus_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("travel_date"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats"),
                        rs.getDouble("fare")
                );

                bus.displayBus();
            }

            if (!found) {
                System.out.println("No buses available.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==========================================
    // SEARCH BUS
    // ==========================================

    public void searchBus(String source, String destination) {

        String sql = "SELECT * FROM buses "
                   + "WHERE source = ? AND destination = ?";

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, source);
            ps.setString(2, destination);

            ResultSet rs = ps.executeQuery();

            boolean found = false;

            while (rs.next()) {

                found = true;

                Bus bus = new Bus(
                        rs.getInt("bus_id"),
                        rs.getString("bus_name"),
                        rs.getString("source"),
                        rs.getString("destination"),
                        rs.getString("travel_date"),
                        rs.getInt("total_seats"),
                        rs.getInt("available_seats"),
                        rs.getDouble("fare")
                );

                bus.displayBus();
            }

            if (!found) {
                System.out.println("No buses found.");
            }

        } catch (Exception e) {

            e.printStackTrace();
        }
    }


    // ==========================================
    // GET ALL BUSES FOR DASHBOARD
    // ==========================================

    public Object[][] getAllBusesForDashboard() {

        String sql = "SELECT * FROM buses";

        java.util.ArrayList<Object[]> list =
                new java.util.ArrayList<>();

        try (Connection con = DBConnection.getConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Object[] row = {

                    rs.getInt("bus_id"),

                    rs.getString("bus_name"),

                    rs.getString("source"),

                    rs.getString("destination"),

                    rs.getString("travel_date"),

                    rs.getInt("available_seats"),

                    "₹" + rs.getDouble("fare")
                };

                list.add(row);
            }

        } catch (Exception e) {

            e.printStackTrace();
        }

        return list.toArray(new Object[0][]);
    }
}

