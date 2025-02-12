package streaming.live_music;

public class JobRequest {
    private int id;
    private String client;
    private String eventTitle;
    private String artist;
    private String date;
    private String time;
    private int duration;
    private int targetAudience;
    private String type;
    private String status;


    public JobRequest(
            int id, String client, String eventTitle, String artist,
            String date, String time, int duration, int targetAudience,
            String type, String status)
    {
        this.id = id;
        this.client = client;
        this.eventTitle = eventTitle;
        this.artist = artist;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.targetAudience = targetAudience;
        this.type = type;
        this.status = status;
    }


    public int getId() { return id; }
    public String getClient() { return client; }
    public String getEventTitle() { return eventTitle; }
    public String getArtist() { return artist; }
    public String getDate() { return date; }
    public String getTime() { return time; }
    public int getDuration() { return duration; }
    public int getTargetAudience() { return targetAudience; }
    public String getType() { return type; }
    public String getStatus() { return status; }
}
