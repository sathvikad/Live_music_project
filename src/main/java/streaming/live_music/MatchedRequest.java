package streaming.live_music;

public class MatchedRequest {
    private String eventName;
    private String venueName;
    private String status;

    public MatchedRequest(String eventName, String venueName, String status) {
        this.eventName = eventName;
        this.venueName = venueName;
        this.status = status;
    }

    public String getEventName() {
        return eventName;
    }

    public String getVenueName() {
        return venueName;
    }

    public String getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "Event: " + eventName + " | Venue: " + venueName + " | Status: " + status;
    }
}