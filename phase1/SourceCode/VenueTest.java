package phase1.SourceCode;

import java.util.Scanner;

public class VenueTest {

    static Scanner scanner = new Scanner(System.in);
    static Venue[] venues = new Venue[10]; // array to hold venues
    static int venueCount = 0;
    static int totalEventCount = 0;
    static int totalTicketCount = 0;
    static Sponsor[] sponsors = new Sponsor[50]; // array to hold sponsors
    static int sponsorCount = 0;
    static Visitor[] visitors = new Visitor[300]; // array to hold visitors
    static int visitorCount = 0;

    public static void main(String[] args) {
        mainMenu();
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
        System.out.println("0. Exit");
        System.out.println("================================================");
    }

    public static void addVenue() {
        System.out.println("\nAdding a venue:");
        System.out.print("Enter venue ID: ");
        String venueID = scanner.nextLine();
        System.out.print("Enter venue name: ");
        String venueName = scanner.nextLine();
        System.out.print("Enter Venue Capacity of events: ");
        int capacity = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        Venue venue = null;
        boolean isTypeTrue = false;
        while (!isTypeTrue) {
            System.out.println("Select venue type:");
            System.out.println("1. Industrial Complex");
            System.out.println("2. Conference Center");
            System.out.print("enter type (1 or 2): ");
            int venueType = scanner.nextInt();
            scanner.nextLine(); // to remove remaining \n from past input


            if (venueType == 1) {
                System.out.print("Enter factory type: ");
                String factoryType = scanner.nextLine();
                venue = new IndustrialComplex(venueID, venueName, capacity, factoryType);
                isTypeTrue=true;
            } else if (venueType == 2) {
                System.out.print("Enter number of meeting rooms: ");
                int meetingRooms = scanner.nextInt();
                scanner.nextLine(); // to remove remaining \n from past input
                venue = new ConferenceCenter(venueID, venueName, capacity, meetingRooms);
                isTypeTrue=true;
            } else {
                System.out.println("Invalid venue type.");
            }
        }
        if (venueCount < venues.length) {
            venues[venueCount] = venue;
            venueCount++;
            System.out.println("Venue added.");
        } else {
            System.out.println("Venue array is full. Cannot add more venues.");
        }
    }

    public static void addEvent() {
        System.out.println("\nAdding an Event:");

        if (venueCount == 0) {
            System.out.println("No venues available to host events. Please add a venue first.");
            return;
        }
        System.out.println("\nAvailable Venues:");
        for (int i = 0; i < venueCount; i++) {
            System.out.println((i + 1) + ". " + venues[i].getName() + " (ID: " + venues[i].getVenueID() + ")");
        }
        System.out.print("Select venue number to host the event : ");
        int venueIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (venueIndex > 0 && venueIndex <= venueCount) {
            System.out.print("Enter event ID: ");
            String eventID = scanner.nextLine();
            System.out.print("Enter event date:");
            String eventDate = scanner.nextLine();

            Event event = null;
            boolean isTypeTrue = false;
            while (!isTypeTrue) {
                System.out.println("select event type:");
                System.out.println("1. business event");
                System.out.println("2. manufacturing event");
                System.out.print("enter type (1 or 2): ");
                int eventType = scanner.nextInt();
                scanner.nextLine(); // to remove remaining \n from past input


                if (eventType == 1) {
                    System.out.print("Enter industry sector: ");
                    String industrySector = scanner.nextLine();
                    event = new BusinessEvent(eventID, eventDate, industrySector);
                    isTypeTrue=true;
                } else if (eventType == 2) {
                    System.out.print("Enter Machinery Type: ");
                    String machineryType = scanner.nextLine();
                    System.out.print("Enter Equipment Cost for Manufacturing Event: ");
                    double equipmentCost = scanner.nextDouble();
                    scanner.nextLine(); // to remove remaining \n from past input
                    event = new ManufacturingEvent(eventID, eventDate, machineryType, equipmentCost);
                    isTypeTrue=true;
                } else {
                    System.out.println("Invalid event type.");
                }
            }
            if (venues[venueIndex - 1].addEvent(event)) {
                System.out.println("Event " + event.getEventID() + " assigned to venue " + venues[venueIndex - 1].getName() + ".");
                totalEventCount++;
            } else {
                System.out.println("Failed to assign event " + event.getEventID() + " to venue " + venues[venueIndex - 1].getName() + " (Venue may be full).");
            }
        } else {
            System.out.println("Invalid venue selection.");
        }
    }

    public static void addTicketToEvent() {

        if (totalEventCount == 0) {
            System.out.println("No events available to add tickets to. Please add an event first.");
            return;
        }
        System.out.println("\nAdding Ticket to Event:");
        System.out.println("Available Events:");

        Event[] currentEvents = new Event[totalEventCount];
        int eventCount =0;
        for (int i = 0; i < venueCount; i++) {
            for ( int j = 0; j < venues[i].getEventCount();j++){
                currentEvents[eventCount] = venues[i].getEvents()[j];
                System.out.println((++eventCount) + ". " + venues[i].getEvents()[j].getEventID() + " (Date: " + venues[i].getEvents()[j].getDate() + ")");
            }
        }
        System.out.print("Select event number to add ticket to: ");
        int eventIndex = scanner.nextInt();
        scanner.nextLine(); // to remove remaining \n from past input

        if (eventIndex > 0 && eventIndex <= eventCount) {
            Event selectedEvent = currentEvents[eventIndex - 1];
            if (selectedEvent.getTicketCount()>=selectedEvent.getTickets().length) {
                System.out.println("Event's seats are full. Cannot add more tickets.");
            }
            System.out.print("Enter Ticket ID: ");
            String ticketID = scanner.nextLine();
            System.out.print("Enter Ticket Price: ");
            double price = scanner.nextDouble();
            scanner.nextLine(); // to remove remaining \n from past input

            Ticket ticket = null;
            boolean isTypeTrue = false;
            while (!isTypeTrue) {
                System.out.println("Select Ticket Type:");
                System.out.println("1. General Ticket");
                System.out.println("2. Business VIP Ticket");
                System.out.println("3. Executive VIP Ticket");
                System.out.print("Enter ticket type (1, 2, or 3): ");
                int ticketType = scanner.nextInt();
                scanner.nextLine(); // to remove remaining \n from past input

                if (ticketType == 1) {
                    System.out.print("Enter Seat Number: ");
                    String seatNumber = scanner.nextLine();
                    ticket = new GeneralTicket(ticketID, price, seatNumber);
                    isTypeTrue=true;
                } else if (ticketType == 2) {
                    System.out.print("Lounge Access (true/false): ");
                    boolean loungeAccess = scanner.nextBoolean();
                    scanner.nextLine(); // to remove remaining \n from past input
                    ticket = new BusinessVIPTicket(ticketID, price, loungeAccess);
                    isTypeTrue=true;
                } else if (ticketType == 3) {
                    System.out.print("Lounge Access (true/false): ");
                    boolean loungeAccess = scanner.nextBoolean();
                    System.out.print("Private Dining Access (true/false): ");
                    boolean privateDiningAccess = scanner.nextBoolean();
                    scanner.nextLine(); // to remove remaining \n from past input
                    ticket = new ExecutiveVIPTicket(ticketID, price, loungeAccess, privateDiningAccess);
                    isTypeTrue=true;
                } else {
                    System.out.println("Invalid ticket type.");
                }
            }
            if (selectedEvent.addTicket(ticket)) {
                System.out.println("Ticket " + ticketID + " added to event " + selectedEvent.getEventID() + ".");
                totalTicketCount++;
            } else {
                System.out.println("Ticket is already added to event.");
            }
        } else {
            System.out.println("Invalid event selection.");
        }
    }

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
}