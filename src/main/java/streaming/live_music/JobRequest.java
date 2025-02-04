
package streaming.live_music;

public class JobRequest {
    private String eventName;
    private String eventDate;
    private String client;

    public JobRequest(String eventName, String eventDate, String client) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.client = client;
    }

    public String getEventName() {
        return eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public String getClient() {
        return client;
    }
}
