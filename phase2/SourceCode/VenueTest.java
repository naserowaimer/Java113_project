package phase2.SourceCode;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.Scanner;
import javax.swing.JOptionPane; // Re-enabled for other methods still using console input
import javax.swing.SwingUtilities;

public class VenueTest {

    static Scanner scanner = new Scanner(System.in); // No longer needed for GUI input
    static Venue[] venues = new Venue[10]; // array to hold venues
    static int venueCount = 0;
    static int totalEventCount = 0;
    static int totalTicketCount = 0;
    static Sponsor[] sponsors = new Sponsor[50]; // array to hold sponsors
    static int sponsorCount = 0;
    static Visitor[] visitors = new Visitor[300]; // array to hold visitors
    static int visitorCount = 0;

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new GUI());
        // mainMenu();
    }

    public static void mainMenu() {
        int choice;
        do {
            displayMainMenu();
            System.out.print("Enter your choice: ");
            choice = scanner.nextInt();
            scanner.nextLine(); //to remove \n from past input

            switch (choice) {
                case 1:
                    addVenue();
                    break;
                case 2:
                    addEvent();
                    break;
                case 3:
                    addTicketToEvent();
                    break;
                case 4:
                    createSponser();
                    break;
                case 5:
                    addSponsorToEvent();
                    break;
                case 6:
                    createVisitorAndBuyTicket();
                    break;
                case 7:
                    calculateEventRevenue();
                    break;
                case 8:
                    displayTicketDetails();
                    break;
                case 9:
                    listEventsForVenue();
                    break;
                case 10:
                    removeEventFromVenue();
                    break;
                case 11:
                    searchEventInVenue();
                    break;
                case 12:
                    printVenuesToFile();
                    break;
                case 13:
                    readOutputFile();
                    break;
                case 0:
                    System.out.println("Exiting program.");
                    break;
                default:
                    System.out.println("Invalid choice. Please try again.");
            }
            System.out.println();
        } while (choice != 0);
    }

    public static void displayMainMenu() {
        System.out.println("\n======== Business Event Management System ========");
        System.out.println("1. Add Venue");
        System.out.println("2. Add Event");
        System.out.println("3. Add Ticket to Event");
        System.out.println("4. Create new Sponsor");
        System.out.println("5. Add Sponsor to Event");
        System.out.println("6. Create Visitor and Buy Ticket");
        System.out.println("7. Calculate Event Revenue");
        System.out.println("8. Display Ticket Details");
        System.out.println("9. List Events for a Venue");
        System.out.println("10. Remove Event from Venue");
        System.out.println("11. Search Event in Venue");
        System.out.println("12. Print all Venues to file");
        System.out.println("13. Read Venues from Venues file");
        System.out.println("0. Exits");
        System.out.println("================================================");
    }

    // Modified addVenue to use JOptionPane for GUI interaction
    public static void addVenue() {
        String venueID = JOptionPane.showInputDialog(null, "Enter venue ID:", "Add Venue", JOptionPane.QUESTION_MESSAGE);
        if (venueID == null || venueID.trim().isEmpty()) {
            // User cancelled or entered empty ID
            if (venueID != null) { // Only show error if not cancelled
                 JOptionPane.showMessageDialog(null, "Venue ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        String venueName = JOptionPane.showInputDialog(null, "Enter venue name:", "Add Venue", JOptionPane.QUESTION_MESSAGE);
        if (venueName == null || venueName.trim().isEmpty()) {
             // User cancelled or entered empty name
            if (venueName != null) { // Only show error if not cancelled
                JOptionPane.showMessageDialog(null, "Venue name cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
            return;
        }

        int capacity = 0;
        boolean validCapacity = false;
        while (!validCapacity) {
            String capacityStr = JOptionPane.showInputDialog(null, "Enter Venue Capacity (positive integer):", "Add Venue", JOptionPane.QUESTION_MESSAGE);
            if (capacityStr == null) return; // User cancelled
            try {
                capacity = Integer.parseInt(capacityStr);
                if (capacity <= 0) {
                    JOptionPane.showMessageDialog(null, "Capacity must be a positive integer.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validCapacity = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for capacity.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Venue venue = null;
        String[] options = {"Industrial Complex", "Conference Center"};
        int venueTypeChoice = JOptionPane.showOptionDialog(null, "Select venue type:", "Add Venue",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, options, options[0]);

        if (venueTypeChoice == 0) { // Industrial Complex
            String factoryType = JOptionPane.showInputDialog(null, "Enter factory type:", "Add Industrial Complex", JOptionPane.QUESTION_MESSAGE);
             if (factoryType == null) return; // User cancelled
             if (factoryType.trim().isEmpty()){
                 JOptionPane.showMessageDialog(null, "Factory type cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 addVenue(); // Restart the process
                 return;
             }
            venue = new IndustrialComplex(venueID, venueName, capacity, factoryType);
        } else if (venueTypeChoice == 1) { // Conference Center
            int meetingRooms = 0;
            boolean validRooms = false;
            while (!validRooms) {
                String roomsStr = JOptionPane.showInputDialog(null, "Enter number of meeting rooms (positive integer):", "Add Conference Center", JOptionPane.QUESTION_MESSAGE);
                 if (roomsStr == null) return; // User cancelled
                try {
                    meetingRooms = Integer.parseInt(roomsStr);
                     if (meetingRooms <= 0) {
                         JOptionPane.showMessageDialog(null, "Number of meeting rooms must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                     } else {
                        validRooms = true;
                     }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for meeting rooms.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            venue = new ConferenceCenter(venueID, venueName, capacity, meetingRooms);
        } else {
             // User closed the dialog or cancelled (-1 or JOptionPane.CLOSED_OPTION)
             return;
        }

        // Add the created venue to the array
        if (venueCount < venues.length) {
            venues[venueCount] = venue;
            venueCount++;
            JOptionPane.showMessageDialog(null, "Venue '" + venueName + "' added successfully.", "Venue Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Venue array is full. Cannot add more venues.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Modified addEvent to use JOptionPane for GUI interaction
    public static void addEvent() {
        if (venueCount == 0) {
            JOptionPane.showMessageDialog(null, "No venues available to host events. Please add a venue first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prepare venue list for selection
        String[] venueNames = new String[venueCount];
        for (int i = 0; i < venueCount; i++) {
            venueNames[i] = venues[i].getName() + " (ID: " + venues[i].getVenueID() + ")";
        }

        Object selectedVenueObj = JOptionPane.showInputDialog(null, "Select venue to host the event:",
                "Add Event", JOptionPane.QUESTION_MESSAGE, null, venueNames, venueNames[0]);

        if (selectedVenueObj == null) return; // User cancelled

        // Find the selected venue index
        int venueIndex = -1;
        for (int i = 0; i < venueCount; i++) {
            if (venueNames[i].equals(selectedVenueObj.toString())) {
                venueIndex = i;
                break;
            }
        }
         if (venueIndex == -1) { // Should not happen with dropdown, but good practice
             JOptionPane.showMessageDialog(null, "Invalid venue selection.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
         }

        Venue selectedVenue = venues[venueIndex];

        String eventID = JOptionPane.showInputDialog(null, "Enter event ID:", "Add Event", JOptionPane.QUESTION_MESSAGE);
        if (eventID == null || eventID.trim().isEmpty()) {
             if (eventID != null) JOptionPane.showMessageDialog(null, "Event ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        String eventDate = JOptionPane.showInputDialog(null, "Enter event date (e.g., YYYY-MM-DD):", "Add Event", JOptionPane.QUESTION_MESSAGE);
         if (eventDate == null || eventDate.trim().isEmpty()) {
             if (eventDate != null) JOptionPane.showMessageDialog(null, "Event date cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        Event event = null;
        String[] eventOptions = {"Business Event", "Manufacturing Event"};
        int eventTypeChoice = JOptionPane.showOptionDialog(null, "Select event type:", "Add Event",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, eventOptions, eventOptions[0]);

        if (eventTypeChoice == 0) { // Business Event
            String industrySector = JOptionPane.showInputDialog(null, "Enter industry sector:", "Add Business Event", JOptionPane.QUESTION_MESSAGE);
            if (industrySector == null) return; // User cancelled
             if (industrySector.trim().isEmpty()){
                 JOptionPane.showMessageDialog(null, "Industry sector cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 addEvent(); // Restart
                 return;
             }
            event = new BusinessEvent(eventID, eventDate, industrySector);
        } else if (eventTypeChoice == 1) { // Manufacturing Event
            String machineryType = JOptionPane.showInputDialog(null, "Enter Machinery Type:", "Add Manufacturing Event", JOptionPane.QUESTION_MESSAGE);
            if (machineryType == null) return; // User cancelled
             if (machineryType.trim().isEmpty()){
                 JOptionPane.showMessageDialog(null, "Machinery type cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                 addEvent(); // Restart
                 return;
             }

            double equipmentCost = 0;
            boolean validCost = false;
            while (!validCost) {
                String costStr = JOptionPane.showInputDialog(null, "Enter Equipment Cost (positive number):", "Add Manufacturing Event", JOptionPane.QUESTION_MESSAGE);
                if (costStr == null) return; // User cancelled
                try {
                    equipmentCost = Double.parseDouble(costStr);
                    if (equipmentCost <= 0) {
                        JOptionPane.showMessageDialog(null, "Equipment cost must be positive.", "Input Error", JOptionPane.ERROR_MESSAGE);
                    } else {
                        validCost = true;
                    }
                } catch (NumberFormatException e) {
                    JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for equipment cost.", "Input Error", JOptionPane.ERROR_MESSAGE);
                }
            }
            event = new ManufacturingEvent(eventID, eventDate, machineryType, equipmentCost);
        } else {
            // User closed the dialog or cancelled
            return;
        }

        // Attempt to add the event to the venue
        if (selectedVenue.addEvent(event)) {
            totalEventCount++;
            JOptionPane.showMessageDialog(null, "Event '" + eventID + "' added to venue '" + selectedVenue.getName() + "'.", "Event Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(null, "Failed to add event '" + eventID + "' to venue '" + selectedVenue.getName() + "'.\nReason: Venue might be full or event ID already exists.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Modified addTicketToEvent to use JOptionPane for GUI interaction
    public static void addTicketToEvent() {
        if (totalEventCount == 0) {
            JOptionPane.showMessageDialog(null, "No events available to add tickets to. Please add an event first.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Prepare event list for selection
        // Need a dynamic way to store events as they might not fill totalEventCount contiguously if events were removed
        java.util.ArrayList<Event> availableEventsList = new java.util.ArrayList<>();
        java.util.ArrayList<String> eventDescriptions = new java.util.ArrayList<>();
        for (int i = 0; i < venueCount; i++) {
            for (int j = 0; j < venues[i].getEventCount(); j++) {
                 if (venues[i].getEvents()[j] != null) { // Check if event exists
                    availableEventsList.add(venues[i].getEvents()[j]);
                    eventDescriptions.add(venues[i].getEvents()[j].getEventID() + " @ " + venues[i].getName() + " (" + venues[i].getEvents()[j].getDate() + ")");
                 }
            }
        }

        if (availableEventsList.isEmpty()) {
             JOptionPane.showMessageDialog(null, "No valid events found.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }

        String[] eventOptionsArr = eventDescriptions.toArray(new String[0]);
        Object selectedEventObj = JOptionPane.showInputDialog(null, "Select event to add ticket to:",
                "Add Ticket", JOptionPane.QUESTION_MESSAGE, null, eventOptionsArr, eventOptionsArr[0]);

        if (selectedEventObj == null) return; // User cancelled

        // Find the selected event object
        Event selectedEvent = null;
        for (int i = 0; i < eventOptionsArr.length; i++) {
            if (eventOptionsArr[i].equals(selectedEventObj.toString())) {
                selectedEvent = availableEventsList.get(i);
                break;
            }
        }

        if (selectedEvent == null) { // Should not happen
            JOptionPane.showMessageDialog(null, "Invalid event selection.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        // Check if event can hold more tickets (assuming getTickets() returns the max capacity array)
        if (selectedEvent.getTicketCount() >= selectedEvent.getTickets().length) {
             JOptionPane.showMessageDialog(null, "Event '" + selectedEvent.getEventID() + "' seats are full. Cannot add more tickets.", "Error", JOptionPane.ERROR_MESSAGE);
             return;
        }

        String ticketID = JOptionPane.showInputDialog(null, "Enter Ticket ID:", "Add Ticket", JOptionPane.QUESTION_MESSAGE);
        if (ticketID == null || ticketID.trim().isEmpty()) {
             if (ticketID != null) JOptionPane.showMessageDialog(null, "Ticket ID cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }

        double price = 0;
        boolean validPrice = false;
        while (!validPrice) {
            String priceStr = JOptionPane.showInputDialog(null, "Enter Ticket Price (positive number):", "Add Ticket", JOptionPane.QUESTION_MESSAGE);
            if (priceStr == null) return; // User cancelled
            try {
                price = Double.parseDouble(priceStr);
                if (price <= 0) {
                    JOptionPane.showMessageDialog(null, "Price must be a positive number.", "Input Error", JOptionPane.ERROR_MESSAGE);
                } else {
                    validPrice = true;
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(null, "Invalid input. Please enter a number for price.", "Input Error", JOptionPane.ERROR_MESSAGE);
            }
        }

        Ticket ticket = null;
        String[] ticketTypes = {"General Ticket", "Business VIP Ticket", "Executive VIP Ticket"};
        int ticketTypeChoice = JOptionPane.showOptionDialog(null, "Select Ticket Type:", "Add Ticket",
                JOptionPane.DEFAULT_OPTION, JOptionPane.INFORMATION_MESSAGE, null, ticketTypes, ticketTypes[0]);

        switch (ticketTypeChoice) {
            case 0: // General Ticket
                String seatNumber = JOptionPane.showInputDialog(null, "Enter Seat Number:", "Add General Ticket", JOptionPane.QUESTION_MESSAGE);
                if (seatNumber == null) return; // User cancelled
                 if (seatNumber.trim().isEmpty()){
                     JOptionPane.showMessageDialog(null, "Seat Number cannot be empty.", "Input Error", JOptionPane.ERROR_MESSAGE);
                     addTicketToEvent(); // Restart
                     return;
                 }
                ticket = new GeneralTicket(ticketID, price, seatNumber);
                break;
            case 1: // Business VIP Ticket
                int loungeChoice = JOptionPane.showConfirmDialog(null, "Grant Lounge Access?", "Add Business VIP Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                if (loungeChoice == JOptionPane.CLOSED_OPTION) return; // User cancelled/closed
                boolean loungeAccess = (loungeChoice == JOptionPane.YES_OPTION);
                ticket = new BusinessVIPTicket(ticketID, price, loungeAccess);
                break;
            case 2: // Executive VIP Ticket
                 int execLoungeChoice = JOptionPane.showConfirmDialog(null, "Grant Lounge Access?", "Add Executive VIP Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                 if (execLoungeChoice == JOptionPane.CLOSED_OPTION) return;
                 boolean execLoungeAccess = (execLoungeChoice == JOptionPane.YES_OPTION);

                 int diningChoice = JOptionPane.showConfirmDialog(null, "Grant Private Dining Access?", "Add Executive VIP Ticket", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
                 if (diningChoice == JOptionPane.CLOSED_OPTION) return;
                 boolean privateDiningAccess = (diningChoice == JOptionPane.YES_OPTION);

                ticket = new ExecutiveVIPTicket(ticketID, price, execLoungeAccess, privateDiningAccess);
                break;
            default:
                // User closed the dialog or cancelled
                return;
        }

        // Attempt to add the ticket to the event
        if (selectedEvent.addTicket(ticket)) {
            totalTicketCount++; // Increment global count if needed (consider if this is the right place)
            JOptionPane.showMessageDialog(null, "Ticket '" + ticketID + "' added to event '" + selectedEvent.getEventID() + "'.", "Ticket Added", JOptionPane.INFORMATION_MESSAGE);
        } else {
            // Assuming addTicket returns false if ticket ID already exists or another issue
            JOptionPane.showMessageDialog(null, "Failed to add ticket '" + ticketID + "' to event '" + selectedEvent.getEventID() + "'.\nReason: Ticket ID might already exist for this event.", "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Keep original createSponser for now
    public static void createSponser(){
        if(sponsorCount>=sponsors.length) {
            System.out.println("Sponsor count exceeds maximum number of sponsors.");
            return;
        }

        System.out.println("\nCreating Sponsor:");

        System.out.print("Enter Sponsor ID: ");
        String sponsorID = scanner.nextLine();
        for (int i = 0; i < sponsorCount; i++) {
            if (sponsorID.equals(sponsors[i].getSponsorID())) {
                System.out.println("Sponsor " + sponsorID + " already exists.");
                return;
            }
        }
        System.out.print("Enter Sponsor  (Riyals): ");
        double contribution = scanner.nextDouble();
        scanner.nextLine(); // to remove remaining \n from past input
        sponsors[sponsorCount] = new Sponsor(sponsorID, contribution);;
        sponsorCount++;
        System.out.println("Sponsor " + sponsorID + " Created.");
    }

    public static void addSponsorToEvent() {
        if (totalEventCount == 0) {
            System.out.println("No events available to add sponsors to. Please add an event first.");
            return;
        }
        if (sponsorCount==0){
            System.out.println("No sponsors available to add event to. Please add a sponsor first.");
            return;
        }
        System.out.println("\nAdding Sponsor to Event:");
        System.out.println("Available Events:");

        Event[] currentEvents = new Event[totalEventCount];
        int eventCount =0;
        for (int i = 0; i < venueCount; i++) {
            for ( int j = 0; j < venues[i].getEventCount();j++){
                currentEvents[eventCount] = venues[i].getEvents()[j];
                System.out.println((++eventCount) + ". " + venues[i].getEvents()[j].getEventID() + " (Date: " + venues[i].getEvents()[j].getDate() + ")");
            }
        }
        System.out.print("Select event number to add sponsor to: ");
        int eventIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (eventIndex > 0 && eventIndex <= eventCount) {
            Event selectedEvent = currentEvents[eventIndex - 1];


            System.out.println("Available Sponsors:");
            for (int i = 0; i < sponsorCount; i++) {
                System.out.println((i+1) + ". " + sponsors[i].getSponsorID());
            }
            System.out.print("Select sponsor number to add to the event: ");
            int sponsorIndex = scanner.nextInt();
            scanner.nextLine(); // to remove remaining \n from past input

            if (selectedEvent.addSponsor(sponsors[sponsorIndex])){
                System.out.println("Sponsor "+ sponsors[sponsorIndex-1].getSponsorID() + "added to event " + selectedEvent.getEventID() + ".");
            }
        } else {
            System.out.println("Invalid event selection.");
        }
    }

    public static void createVisitorAndBuyTicket() {
        if(totalTicketCount==0){
            System.out.println("No tickets available for purchase. Please add tickets to events first.");
            return;
        }
        if (visitorCount >= visitors.length){
            System.out.println("Visitor array is full. Cannot add more visitors.");
            return;
        }
        System.out.println("Available Tickets:");
        Ticket[] currentTickets = new Ticket[totalTicketCount];
        int ticketCount =0;
        for (int i = 0; i < totalTicketCount; i++) {
            for ( int j = 0; j < venues[i].getEventCount();j++){
                for ( int k = 0; k < venues[i].getEvents()[j].getTicketCount();k++){
                    currentTickets[ticketCount] = venues[i].getEvents()[j].getTickets()[k];
                    System.out.println((++ticketCount) + ". "+ venues[i].getEvents()[j].getTickets()[k].getTicketID() +" ("+ venues[i].getEvents()[j].getTickets()[k].getPrice() + " Riyal)");
                }
            }
        }
        System.out.print("Select ticket number to buy: ");
        int ticketIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input


        System.out.println("\nCreating Visitor and Buying Ticket:");
        System.out.print("Enter Visitor ID: ");
        String visitorID = scanner.nextLine();
        for(int i =0; i<visitorCount; i++) {
            if (visitorID.equals(visitors[i].getVisitorID())) {
                System.out.println("Visitor " + visitorID + " already exists.");
                return;
            }
        }

        Visitor visitor = new Visitor(visitorID);
        visitors[visitorCount] = visitor;
        visitorCount++;


        if (ticketIndex > 0 && ticketIndex <= ticketCount) {
            visitor.buyTicket(currentTickets[ticketIndex - 1]);
            System.out.println("Visitor " + visitorID + " bought ticket " + currentTickets[ticketIndex - 1].getTicketID() + ".");
        } else {
            System.out.println("Invalid ticket selection.");
        }
    }


    public static void calculateEventRevenue() {
        if (totalEventCount == 0) {
            System.out.println("No events available to calculate revenue. Please add an event first.");
            return;
        }
        System.out.println("\nCalculating Event Revenue:");
        System.out.println("Available Events:");
        Event[] currentEvents = new Event[totalEventCount];
        int eventCount =0;
        for (int i = 0; i < venueCount; i++) {
            for ( int j = 0; j < venues[i].getEventCount();j++){
                currentEvents[eventCount] = venues[i].getEvents()[j];
                System.out.println((++eventCount) + ". " + venues[i].getEvents()[j].getEventID() + " (Date: " + venues[i].getEvents()[j].getDate() + ")");
            }
        }
        System.out.print("Select event number to calculate revenue for: ");
        int eventIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (eventIndex > 0 && eventIndex <= eventCount) {
            Event selectedEvent = currentEvents[eventIndex - 1];
            double revenue = selectedEvent.calculateRevenue();
            System.out.println("Revenue for event " + selectedEvent.getEventID() + ": Riyal " + revenue);
        } else {
            System.out.println("Invalid event selection.");
        }
    }

    public static void displayTicketDetails() {
        if (totalTicketCount == 0) {
            System.out.println("No tickets available to display details. Please add tickets to events first.");
            return;
        }
        System.out.println("\nDisplaying Ticket Details:");
        System.out.println("Available Tickets:");
        Ticket[] currentTickets = new Ticket[totalTicketCount];
        int ticketCount =0;
        for (int i = 0; i < totalTicketCount; i++) {
            for ( int j = 0; j < venues[i].getEventCount();j++){
                for ( int k = 0; k < venues[i].getEvents()[j].getTicketCount();k++){
                    currentTickets[ticketCount] = venues[i].getEvents()[j].getTickets()[k];
                    System.out.println((++ticketCount) + ". "+ venues[i].getEvents()[j].getTickets()[k].getTicketID() +" ("+ venues[i].getEvents()[j].getTickets()[k].getPrice() + " Riyal)");
                }
            }
        }
        System.out.print("Select ticket number to display details for: ");
        int ticketIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (ticketIndex > 0 && ticketIndex <= ticketCount) {
            Ticket selectedTicket = currentTickets[ticketIndex - 1];
            selectedTicket.displayTicketDetails();
        } else {
            System.out.println("Invalid ticket selection.");
        }
    }

    public static void listEventsForVenue() {
        if (venueCount == 0) {
            System.out.println("No venues available. Please add a venue first.");
            return;
        }
        System.out.println("\nListing Events for a Venue:");
        System.out.println("Available Venues:");
        for (int i = 0; i < venueCount; i++) {
            System.out.println((i + 1) + ". " + venues[i].getName() + " (ID: " + venues[i].getVenueID() + ")");
        }
        System.out.print("Select venue number to list events for: ");
        int venueIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (venueIndex > 0 && venueIndex <= venueCount) {
            Venue selectedVenue = venues[venueIndex - 1];
            System.out.println("Events at " + selectedVenue.getName() + ":");
            Event[] venueEvents = selectedVenue.getEvents();
            for(int i = 0; i < venueEvents.length; i++) {
                if (venueEvents[i] != null) {
                    System.out.println("- " + venueEvents[i].getEventID() + " (Date: " + venueEvents[i].getDate() + ")");
                }
            }
        } else {
            System.out.println("Invalid venue selection.");
        }
    }

    public static void removeEventFromVenue() {
        if (venueCount == 0) {
            System.out.println("No venues available. Please add a venue first.");
            return;
        }
        System.out.println("\nRemoving Event from Venue:");
        System.out.println("Available Venues:");
        for (int i = 0; i < venueCount; i++) {
            System.out.println((i + 1) + ". " + venues[i].getName() + " (ID: " + venues[i].getVenueID() + ")");
        }
        System.out.print("Select venue number to remove event from: ");
        int venueIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (venueIndex > 0 && venueIndex <= venueCount) {
            Venue selectedVenue = venues[venueIndex - 1];
            System.out.println("Events at " + selectedVenue.getName() + ":");
            Event[] venueEvents = selectedVenue.getEvents();
            if (venueEvents == null || venueEvents.length == 0) {
                System.out.println("No events currently hosted at this venue.");
                return;
            }
            for (int i = 0; i < venueEvents.length; i++) {
                if (venueEvents[i] != null) {
                    System.out.println("- " + (i + 1) + ". " + venueEvents[i].getEventID() + " (Date: " + venueEvents[i].getDate() + ")");
                }
            }
            System.out.print("Select event number to remove: ");
            int eventToRemoveIndex = scanner.nextInt();
            scanner.nextLine(); // to remove remaining \n from past input

            int eventCounter = 0;
            String eventIDToRemove = null;
            for (int i = 0; i < venueEvents.length; i++) {
                if (venueEvents[i] != null) {
                    eventCounter++;
                    if (eventCounter == eventToRemoveIndex) {
                        eventIDToRemove = venueEvents[i].getEventID();
                        break;
                    }
                }
            }

            if (eventIDToRemove != null) {
                selectedVenue.removeEvent(eventIDToRemove);
                totalEventCount--;
                System.out.println("Event " + eventIDToRemove + " removed from venue " + selectedVenue.getName() + ".");
            } else {
                System.out.println("Invalid event selection for removal.");
            }

        } else {
            System.out.println("Invalid venue selection.");
        }
    }

    public static void searchEventInVenue() {
        if (venueCount == 0) {
            System.out.println("No venues available. Please add a venue first.");
            return;
        }
        System.out.println("\nSearching Event in Venue:");
        System.out.println("Available Venues:");
        for (int i = 0; i < venueCount; i++) {
            System.out.println((i + 1) + ". " + venues[i].getName() + " (ID: " + venues[i].getVenueID() + ")");
        }
        System.out.print("Select venue number to search event in: ");
        int venueIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (venueIndex > 0 && venueIndex <= venueCount) {
            Venue selectedVenue = venues[venueIndex - 1];
            System.out.print("Enter Event ID to search for in " + selectedVenue.getName() + ": ");
            String searchEventID = scanner.nextLine();
            Event[] venueEvents = selectedVenue.getEvents();
            boolean found = false;
            for (int i = 0; i < venueEvents.length; i++) {
                if (venueEvents[i] != null && venueEvents[i].getEventID().equals(searchEventID)) {
                    System.out.println("Event found at " + selectedVenue.getName() + ":");
                    System.out.println("- Event ID: " + venueEvents[i].getEventID());
                    System.out.println("- Event Date: " + venueEvents[i].getDate());
                    found = true;
                    break;
                }
            }
            if (!found) {
                System.out.println("Event " + searchEventID + " not found at " + selectedVenue.getName() + ".");
            }
        } else {
            System.out.println("Invalid venue selection.");
        }
    }

    public static void printVenuesToFile() {
        File file = new File("venues.txt");
        try {
            if (!file.exists()) {
                file.createNewFile();
            }
            Scanner fileScanner = new Scanner(file);
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            for (int i = 0; i < venueCount; i++) {
                String venueDetails = "- Venue ID: " + venues[i].getVenueID() + ", Name: " + venues[i].getName() + ", Capacity: " + venues[i].getCapacity();
                fileOutputStream.write(venueDetails.getBytes());
                fileOutputStream.write(System.lineSeparator().getBytes());
                if (venues[i].getEvents() == null || venues[i].getEventCount() == 0) {
                    System.out.println("No events currently hosted at this venue.");
                    continue;
                }
                
                Event[] events = venues[i].getEvents();
                for(Event event: events){
                    if (events != null) {
                        String eventDetails = "   - Event ID: " + event.getEventID() + ", Date: " + event.getDate();
                        fileOutputStream.write(eventDetails.getBytes());
                        fileOutputStream.write(System.lineSeparator().getBytes());
                    }
                }
            }
            
            fileScanner.close();
        } catch (Exception e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }

    public static void readOutputFile() {
        File file = new File("venues.txt");
        try {
            if (!file.exists()) {
                throw new FileNotFoundException("File not found.");
            }
            Scanner fileScanner = new Scanner(file);
            while (fileScanner.hasNextLine()) {
                System.out.println(fileScanner.nextLine());
            }
            fileScanner.close();
        } catch (FileNotFoundException e) {
            System.out.println("Error reading from file: " + e.getMessage());
        }
    }
}
