package phase2.SourceCode;

public class BusinessVIPTicket extends Ticket {
    private boolean loungeAccess;

    public BusinessVIPTicket(String ticketID, double price, boolean loungeAccess) {
        super(ticketID, price);
        this.loungeAccess = loungeAccess;
    }

    public boolean hasLoungeAccess() {
        return loungeAccess;
    }

    public void displayTicketDetails() {
        System.out.println("Business VIP Ticket Details:");
        System.out.println("SourceCode.Ticket ID: " + getTicketID());
        System.out.println("Price: Riyal " + getPrice());
        System.out.println("Lounge Access: " + (loungeAccess ? "Yes" : "No"));
    }
}