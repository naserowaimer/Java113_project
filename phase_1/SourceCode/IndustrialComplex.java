package SourceCode;

public class IndustrialComplex extends Venue {
    private String factoryType;

    public IndustrialComplex(String venueID, String name, int capacity, String factoryType) {
        super(venueID, name, capacity);
        this.factoryType = factoryType;
    }

    public String getFactoryType() {
        return factoryType;
    }
}