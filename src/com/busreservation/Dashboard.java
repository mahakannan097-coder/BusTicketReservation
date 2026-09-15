package com.busreservation;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;

public class Dashboard extends JFrame {

    // ==========================================
    // COLORS
    // ==========================================

    Color darkBlue = new Color(30, 55, 90);
    Color lightBlue = new Color(235, 245, 255);
    Color white = Color.WHITE;


    // ==========================================
    // CONSTRUCTOR
    // ==========================================

    public Dashboard() {

        setTitle("Bus Ticket Reservation System");

        setBounds(100, 100, 1100, 650);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        setLocationRelativeTo(null);


        // ==========================================
        // MAIN PANEL
        // ==========================================

        JPanel mainPanel = new JPanel();

        mainPanel.setLayout(new BorderLayout());


        // ==========================================
        // HEADER
        // ==========================================

        JPanel header = new JPanel();

        header.setBackground(darkBlue);

        header.setPreferredSize(
                new Dimension(1100, 80)
        );


        JLabel title = new JLabel(
                "BUS TICKET RESERVATION SYSTEM"
        );

        title.setForeground(white);

        title.setFont(
                new Font("Arial", Font.BOLD, 28)
        );


        header.add(title);


        mainPanel.add(
                header,
                BorderLayout.NORTH
        );


        // ==========================================
        // LEFT MENU
        // ==========================================

        JPanel menuPanel = new JPanel();

        menuPanel.setBackground(darkBlue);

        menuPanel.setPreferredSize(
                new Dimension(230, 520)
        );

        menuPanel.setLayout(
                new GridLayout(6, 1, 10, 10)
        );


        // ==========================================
        // BUTTONS
        // ==========================================

        JButton viewBusButton =
                new JButton("View Buses");

        JButton searchButton =
                new JButton("Search Bus");

        JButton bookButton =
                new JButton("Book Ticket");

        JButton bookingButton =
                new JButton("View Bookings");

        JButton cancelButton =
                new JButton("Cancel Ticket");

        JButton exitButton =
                new JButton("Exit");


        // ==========================================
        // BUTTON FONT
        // ==========================================

        Font buttonFont =
                new Font("Arial", Font.BOLD, 16);


        viewBusButton.setFont(buttonFont);

        searchButton.setFont(buttonFont);

        bookButton.setFont(buttonFont);

        bookingButton.setFont(buttonFont);

        cancelButton.setFont(buttonFont);

        exitButton.setFont(buttonFont);


        // ==========================================
        // ADD BUTTONS
        // ==========================================

        menuPanel.add(viewBusButton);

        menuPanel.add(searchButton);

        menuPanel.add(bookButton);

        menuPanel.add(bookingButton);

        menuPanel.add(cancelButton);

        menuPanel.add(exitButton);


        mainPanel.add(
                menuPanel,
                BorderLayout.WEST
        );


        // ==========================================
        // CENTER PANEL
        // ==========================================

        JPanel centerPanel = new JPanel();

        centerPanel.setBackground(lightBlue);

        centerPanel.setLayout(
                new GridBagLayout()
        );


        JLabel welcomeLabel = new JLabel(
                "<html><center>" +
                "Welcome to<br>" +
                "<br>" +
                "Bus Ticket Reservation System" +
                "<br><br>" +
                "Select an option from the menu" +
                "</center></html>"
        );


        welcomeLabel.setFont(
                new Font("Arial", Font.BOLD, 25)
        );


        centerPanel.add(welcomeLabel);


        mainPanel.add(
                centerPanel,
                BorderLayout.CENTER
        );


        // ==========================================
        // VIEW BUSES BUTTON
        // ==========================================

        viewBusButton.addActionListener(e -> {

            showBuses();

        });


        // ==========================================
        // VIEW BOOKINGS BUTTON
        // ==========================================

        bookingButton.addActionListener(e -> {

            BookingDAO bookingDAO =
                    new BookingDAO();

            bookingDAO.viewBookings();

            JOptionPane.showMessageDialog(
                    this,
                    "Booking details are displayed in the Eclipse Console.",
                    "View Bookings",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // ==========================================
        // SEARCH BUTTON
        // ==========================================

        searchButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Search Bus feature will be added next.",
                    "Search Bus",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // ==========================================
        // BOOK BUTTON
        // ==========================================

        bookButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Book Ticket feature will be added next.",
                    "Book Ticket",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // ==========================================
        // CANCEL BUTTON
        // ==========================================

        cancelButton.addActionListener(e -> {

            JOptionPane.showMessageDialog(
                    this,
                    "Cancel Ticket feature will be added next.",
                    "Cancel Ticket",
                    JOptionPane.INFORMATION_MESSAGE
            );

        });


        // ==========================================
        // EXIT BUTTON
        // ==========================================

        exitButton.addActionListener(e -> {

            int answer = JOptionPane.showConfirmDialog(
                    this,
                    "Do you want to exit?",
                    "Exit",
                    JOptionPane.YES_NO_OPTION
            );


            if (answer == JOptionPane.YES_OPTION) {

                System.exit(0);

            }

        });


        // ==========================================
        // ADD MAIN PANEL
        // ==========================================

        add(mainPanel);

        setVisible(true);
    }


    // ==========================================
    // SHOW BUSES
    // ==========================================

    private void showBuses() {

        BusDAO busDAO = new BusDAO();

        Object[][] data =
                busDAO.getAllBusesForDashboard();


        // Table column names

        String[] columns = {

                "Bus ID",
                "Bus Name",
                "From",
                "To",
                "Travel Date",
                "Available Seats",
                "Fare"
        };


        // Create table

        DefaultTableModel model =
                new DefaultTableModel(
                        data,
                        columns
                ) {

                    @Override
                    public boolean isCellEditable(
                            int row,
                            int column) {

                        return false;
                    }
                };


        JTable table =
                new JTable(model);


        // ==========================================
        // TABLE DESIGN
        // ==========================================

        table.setFont(
                new Font("Arial", Font.PLAIN, 15)
        );

        table.setRowHeight(30);

        table.getTableHeader().setFont(
                new Font("Arial", Font.BOLD, 15)
        );


        // ==========================================
        // SCROLL PANE
        // ==========================================

        JScrollPane scrollPane =
                new JScrollPane(table);


        scrollPane.setPreferredSize(
                new Dimension(950, 400)
        );


        // ==========================================
        // SHOW TABLE
        // ==========================================

        JOptionPane.showMessageDialog(

                this,

                scrollPane,

                "Available Buses",

                JOptionPane.PLAIN_MESSAGE
        );
    }


    // ==========================================
    // MAIN METHOD
    // ==========================================

    public static void main(String[] args) {

        SwingUtilities.invokeLater(() -> {

            new Dashboard();

        });

    }
}