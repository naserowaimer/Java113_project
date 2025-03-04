package SourceCode;

public class GeneralTicket extends Ticket {
    private String seatNumber;

    public GeneralTicket(String ticketID, double price, String seatNumber) {
        super(ticketID, price);
        this.seatNumber = seatNumber;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void displayTicketDetails() {
        System.out.println("General Ticket Details:");
        System.out.println("SourceCode.Ticket ID: " + getTicketID());
        System.out.println("Price: Riyal " + getPrice());
        System.out.println("Seat Number: " + seatNumber);
    }
}