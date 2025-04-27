package phase2.SourceCode;

public class ExecutiveVIPTicket extends BusinessVIPTicket {
    private boolean privateDiningAccess;

    public ExecutiveVIPTicket(String ticketID, double price, boolean loungeAccess, boolean privateDiningAccess) {
        super(ticketID, price, loungeAccess);
        this.privateDiningAccess = privateDiningAccess;
    }

    public boolean hasPrivateDiningAccess() {
        return privateDiningAccess;
    }

    public void displayTicketDetails() {
        System.out.println("Executive VIP Ticket Details:");
        System.out.println("SourceCode.Ticket ID: " + getTicketID());
        System.out.println("Price: Riyal " + getPrice());
        System.out.println("Lounge Access: " + (hasLoungeAccess() ? "Yes" : "No"));
        System.out.println("Private Dining Access: " + (privateDiningAccess ? "Yes" : "No"));
    }

    public void reservePrivateChef() {
        System.out.println("Private chef service reserved for Executive VIP ticket holder.");
    }
}