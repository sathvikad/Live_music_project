package streaming.live_music;

public class JobRequest {
    private String requestId;
    private String eventName;
    private String client;

    public JobRequest(String requestId, String eventName, String client) {
        this.requestId = requestId;
        this.eventName = eventName;
        this.client = client;
    }

    public String getRequestId() {
        return requestId;
    }

    public void setRequestId(String requestId) {
        this.requestId = requestId;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }
}
