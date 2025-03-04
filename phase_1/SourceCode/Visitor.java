package SourceCode;

public class Visitor {
    private String visitorID;
    private Ticket[] tickets;
    private int ticketCount;

    public Visitor(String visitorID) {
        this.visitorID = visitorID;
        this.tickets = new Ticket[10];
        this.ticketCount = 0;
    }

    public String getVisitorID() {
        return visitorID;
    }

    public Ticket[] getTickets() {
        return tickets;
    }

    public void buyTicket(Ticket ticket) {
        if (ticketCount < tickets.length) {
            this.tickets[ticketCount] = ticket;
            ticketCount++;
        } else {
            System.out.println("Visitor " + visitorID + " cannot buy more tickets.");
        }
    }
}