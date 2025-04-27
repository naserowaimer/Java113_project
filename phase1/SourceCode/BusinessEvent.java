package phase1.SourceCode;

public class BusinessEvent extends Event {
    private String industrySector;

    public BusinessEvent(String eventID, String date, String industrySector) {
        super(eventID, date);
        this.industrySector = industrySector;
    }

    public String getIndustrySector() {
        return industrySector;
    }

    public double calculateRevenue() {
        double totalRevenue = 0;
        for (int i = 0; i < getTickets().length; i++) {
            if (getTickets()[i] != null) {
                totalRevenue += getTickets()[i].getPrice();
            }
        }
        return totalRevenue;
    }
}