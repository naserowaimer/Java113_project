package phase2.SourceCode;

public class ManufacturingEvent extends Event {
    private String machineryType;
    private double equipmentCost;

    public ManufacturingEvent(String eventID, String date, String machineryType, double equipmentCost) {
        super(eventID, date);
        this.machineryType = machineryType;
        this.equipmentCost = equipmentCost;
    }

    public String getMachineryType() {
        return machineryType;
    }

    public double getEquipmentCost() {
        return equipmentCost;
    }

    public double calculateRevenue() {
        double ticketRevenue = 0;
        for (int i = 0; i < getTickets().length; i++) {
            if (getTickets()[i] != null) {
                ticketRevenue += getTickets()[i].getPrice();
            }
        }
        return ticketRevenue - equipmentCost;
    }
}