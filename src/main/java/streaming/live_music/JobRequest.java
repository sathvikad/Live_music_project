package streaming.live_music;

public class JobRequest {
    private String clientName;
    private String eventName;
    private String eventDate;
    private int expectedAttendance;

    public JobRequest(String clientName, String eventName, String eventDate, int expectedAttendance) {
        this.clientName = clientName;
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.expectedAttendance = expectedAttendance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getEventName() {
        return eventName;
    }

    public void setEventName(String eventName) {
        this.eventName = eventName;
    }

    public String getEventDate() {
        return eventDate;
    }

    public void setEventDate(String eventDate) {
        this.eventDate = eventDate;
    }

    public int getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(int expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }
}
