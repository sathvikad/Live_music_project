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

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getVenueName() {
        return venueName;
    }

    public void setVenueName(String venueName) {
        this.venueName = venueName;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
