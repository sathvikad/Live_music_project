package streaming.live_music;

public class MatchedRequest {
    private final String eventName;
    private final String venueName;
    private final String status;

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
}
