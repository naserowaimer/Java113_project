package phase1.SourceCode;

public class ConferenceCenter extends Venue {
    private int meetingRooms;

    public ConferenceCenter(String venueID, String name, int capacity, int meetingRooms) {
        super(venueID, name, capacity);
        this.meetingRooms = meetingRooms;
    }

    public int getMeetingRooms() {
        return meetingRooms;
    }
}