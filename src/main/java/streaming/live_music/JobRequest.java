package streaming.live_music;

public class JobRequest {
    private String eventName;
    private String preferredLocation;
    private int expectedAttendees;

    public JobRequest(String eventName, String preferredLocation, int expectedAttendees) {
        this.eventName = eventName;
        this.preferredLocation = preferredLocation;
        this.expectedAttendees = expectedAttendees;
    }

    public String getEventName() {
        return eventName;
    }

    public String getPreferredLocation() {
        return preferredLocation;
    }

    public int getExpectedAttendees() {
        return expectedAttendees;
    }
}
