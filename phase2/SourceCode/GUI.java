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
    private JButton printToFileButton;
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
        printToFileButton.addActionListener(this);
        exitButton.addActionListener(this);

        // Add buttons to the pane
        pane.add(addVenueButton);
        pane.add(addEventButton);
        pane.add(addTicketButton);
        pane.add(printToFileButton);
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
        } else if (source == printToFileButton) {
            VenueTest.printVenuesToFile(); // Uses shared file method
        } else if (source == exitButton) {
            System.exit(0); // Exit the application
        }
    }
}
