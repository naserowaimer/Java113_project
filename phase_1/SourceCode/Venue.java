package SourceCode;

public abstract class Venue {
    private String venueID;
    private String name;
    private int capacity;
    private Event[] events;
    private int eventCount;

    public Venue(String venueID, String name, int capacity) {
        this.venueID = venueID;
        this.name = name;
        this.capacity = capacity;
        this.events = new Event[capacity];
        this.eventCount = 0;
    }

    public String getVenueID() {
        return venueID;
    }

    public String getName() {
        return name;
    }

    public int getCapacity() {
        return capacity;
    }

    public Event[] getEvents() {
        return events;
    }

    public int getEventCount() {
        return eventCount;
    }

    public boolean addEvent(Event event) {
        if (eventCount < capacity) {
            int firstNullIndex = 0;
            for (int i = 0; i < events.length; i++) {
                if (events[i] == null) {
                    firstNullIndex = i;
                    break;
                }
                if (events[i].getEventID().equals(event.getEventID())) {
                    System.out.println("Event with this ID already exists");
                    return false;
                }
            }


            if (event instanceof ManufacturingEvent){
                ManufacturingEvent eventCast = (ManufacturingEvent) event;
                this.events[firstNullIndex] = new ManufacturingEvent(eventCast.getEventID(),eventCast.getDate(),eventCast.getMachineryType(),eventCast.getEquipmentCost());
            }else {
                BusinessEvent eventCast = (BusinessEvent) event;
                this.events[firstNullIndex] = new BusinessEvent(eventCast.getEventID(),eventCast.getDate(),eventCast.getIndustrySector());
            }
            eventCount++;
            return true;
        } else {
            System.out.println("Venue at full capacity, cannot add event: " + event.getEventID());
            return false;
        }
    }

    public void removeEvent(String eventID) {
        for (int i = 0; i < eventCount; i++) {
            if (events[i] != null && events[i].getEventID().equals(eventID)) {
                events[i] = null;

                eventCount--;
                return;
            }
        }
        System.out.println("Event with ID " + eventID + " not found in this venue.");
    }
}