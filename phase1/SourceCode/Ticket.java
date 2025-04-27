package phase1.SourceCode;

public abstract class Ticket {
    private String ticketID;
    private double price;

    public Ticket(String ticketID, double price) {
        this.ticketID = ticketID;
        this.price = price;
    }

    public String getTicketID() {
        return ticketID;
    }

    public double getPrice() {
        return price;
    }

    public abstract void displayTicketDetails();
}