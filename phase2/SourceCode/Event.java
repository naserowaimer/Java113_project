package phase2.SourceCode;

public abstract class Event {
    private String eventID;
    private String date;
    private Ticket[] tickets; // Composition relation
    private int ticketCount;
    private Sponsor[] sponsors;
    private int sponsorCount;

    public Event(String eventID, String date) {
        this.eventID = eventID;
        this.date = date;
        this.tickets = new Ticket[100];
        this.ticketCount = 0;
        this.sponsors = new Sponsor[10];
        this.sponsorCount = 0;
    }

    public String getEventID() {
        return eventID;
    }

    public String getDate() {
        return date;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public Sponsor[] getSponsors() {
        return sponsors;
    }

    public boolean addTicket(Ticket ticket) { // Composition relation
        if (ticketCount < tickets.length) {
            if(ticketCount>0){
            for (int i = 0; i < tickets.length; i++) {
                if (tickets[i].getTicketID().equals(ticket.getTicketID())) {
                    System.out.println("Ticket with this ID already exists");
                    return false;
                }
            }
            }

            if (ticket instanceof ExecutiveVIPTicket) {
                ExecutiveVIPTicket ticketCast = (ExecutiveVIPTicket) ticket;
                this.tickets[ticketCount] = new ExecutiveVIPTicket(ticketCast.getTicketID(),ticketCast.getPrice(),ticketCast.hasLoungeAccess(),ticketCast.hasPrivateDiningAccess());
            } else if (ticket instanceof BusinessVIPTicket) {
                BusinessVIPTicket ticketCast = (BusinessVIPTicket) ticket;
                this.tickets[ticketCount] = new BusinessVIPTicket(ticketCast.getTicketID(),ticketCast.getPrice(),ticketCast.hasLoungeAccess());
            }else {
                GeneralTicket ticketCast = (GeneralTicket) ticket;
                this.tickets[ticketCount] = new GeneralTicket(ticketCast.getTicketID(),ticketCast.getPrice(),ticketCast.getSeatNumber());
            }
            ticketCount++;
            return true;
        } else {
            System.out.println("Event " + eventID + " tickets are sold out for now.");
            return false;
        }
    }

    public int getTicketCount() {
        return ticketCount;
    }
    public boolean addSponsor(Sponsor sponsor) { // aggregation relation
        if (sponsorCount < sponsors.length) {
            if(sponsorCount>0){
                for (int i = 0; i < sponsors.length; i++) {
                    if (sponsors[i].getSponsorID().equals(sponsor.getSponsorID())) {
                        System.out.println("Sponser with this ID already added to this event");
                        return false;
                    }
                }
            }
            this.sponsors[sponsorCount] = sponsor;
            sponsorCount++;
            return true;
        } else {
            System.out.println("Event " + eventID + " cannot accommodate more sponsors.");
            return false;
        }
    }

    public abstract double calculateRevenue();
}