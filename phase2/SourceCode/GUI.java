package phase2.SourceCode;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class GUI extends JFrame implements ActionListener { // Changed to public for instantiation
    private static final int WIDTH = 400; // Adjusted size for buttons
    private static final int HEIGHT = 500; // Adjusted size for buttons
    private static final int X_ORIGIN = 100;
    private static final int Y_ORIGIN = 100;

    // Declare buttons for actions
    private JButton addVenueButton;
    private JButton addEventButton;
    private JButton addTicketButton;
    private JButton createSponsorButton;
    private JButton addSponsorButton;
    private JButton createVisitorButton;
    private JButton calculateRevenueButton;
    private JButton displayTicketButton;
    private JButton listEventsButton;
    private JButton removeEventButton;
    private JButton searchEventButton;
    private JButton printToFileButton;
    private JButton readFromFileButton;
    private JButton exitButton;


    public GUI() {
        Container pane = getContentPane(); // Get the content pane
        pane.setLayout(new GridLayout(0, 1, 10, 10)); // Use GridLayout for vertical buttons

        setSize(WIDTH,HEIGHT);
        setResizable(false);
        setTitle("Business Event Management (GUI Mode)"); // Indicate mode in title
        setLocation(X_ORIGIN,Y_ORIGIN);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Ensure the application exits on close

        // Initialize buttons
        addVenueButton = new JButton("Add Venue");
        addEventButton = new JButton("Add Event");
        addTicketButton = new JButton("Add Ticket to Event");
        printToFileButton = new JButton("Print Venues to file");
        exitButton = new JButton("0. Exit");

        // Add action listeners
        addVenueButton.addActionListener(this);
        addEventButton.addActionListener(this);
        addTicketButton.addActionListener(this);
        createSponsorButton.addActionListener(this);
        addSponsorButton.addActionListener(this);
        createVisitorButton.addActionListener(this);
        calculateRevenueButton.addActionListener(this);
        displayTicketButton.addActionListener(this);
        listEventsButton.addActionListener(this);
        removeEventButton.addActionListener(this);
        searchEventButton.addActionListener(this);
        printToFileButton.addActionListener(this);
        readFromFileButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add buttons to the pane
        pane.add(addVenueButton);
        pane.add(addEventButton);
        pane.add(addTicketButton);
        pane.add(createSponsorButton);
        pane.add(addSponsorButton);
        pane.add(createVisitorButton);
        pane.add(calculateRevenueButton);
        pane.add(displayTicketButton);
        pane.add(listEventsButton);
        pane.add(removeEventButton);
        pane.add(searchEventButton);
        pane.add(printToFileButton);
        pane.add(readFromFileButton);
        pane.add(exitButton);

        setVisible(true); // Make the window visible
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        // These calls now go to the VenueTest methods that handle GUI interaction
        if (source == addVenueButton) {
            VenueTest.addVenue();
        } else if (source == addEventButton) {
            VenueTest.addEvent();
        } else if (source == addTicketButton) {
            VenueTest.addTicketToEvent();
        } else if (source == createSponsorButton) {
            VenueTest.createSponser(); // Calls the GUI-enabled version
        } else if (source == addSponsorButton) {
            VenueTest.addSponsorToEvent();
        } else if (source == createVisitorButton) {
            VenueTest.createVisitorAndBuyTicket();
        } else if (source == calculateRevenueButton) {
            VenueTest.calculateEventRevenue();
        } else if (source == displayTicketButton) {
            VenueTest.displayTicketDetails(); // Requires Ticket class refactoring
        } else if (source == listEventsButton) {
            VenueTest.listEventsForVenue();
        } else if (source == removeEventButton) {
            VenueTest.removeEventFromVenue();
        } else if (source == searchEventButton) {
            VenueTest.searchEventInVenue(); // Requires findEvent in Venue
        } else if (source == printToFileButton) {
            VenueTest.printVenuesToFile(); // Uses shared file method
        } else if (source == readFromFileButton) {
             VenueTest.readOutputFile(); // Uses GUI version
        } else if (source == exitButton) {
            System.exit(0); // Exit the application
        }
    }
}