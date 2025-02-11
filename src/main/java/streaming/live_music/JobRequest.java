package streaming.live_music;

public class JobRequest {
    private String eventName;
    private String eventDate;
    private String clientName;
    private int expectedAttendance;

    public JobRequest(String eventName, String eventDate, String clientName, int expectedAttendance) {
        this.eventName = eventName;
        this.eventDate = eventDate;
        this.clientName = clientName;
        this.expectedAttendance = expectedAttendance;
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

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getExpectedAttendance() {
        return expectedAttendance;
    }

    public void setExpectedAttendance(int expectedAttendance) {
        this.expectedAttendance = expectedAttendance;
    }
}
