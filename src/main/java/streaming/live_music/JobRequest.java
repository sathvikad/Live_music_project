package streaming.live_music;

public class JobRequest {
    private int id;  // Added missing ID field
    private String client;
    private String title;
    private String artist;
    private String date;
    private String time;
    private int duration;
    private int targetAudience;
    private String type;
    private String category;

    /**
     * Constructor for new Job Requests (without ID).
     * Used when adding a new job request before it's assigned an ID in the database.
     */
    public JobRequest(String client, String title, String artist, String date, String time,
                      int duration, int targetAudience, String type, String category) {
        this.client = client;
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.targetAudience = targetAudience;
        this.type = type;
        this.category = category;
    }

    /**
     * Constructor for fetching existing Job Requests (with ID).
     * Used when retrieving job requests from the database.
     */
    public JobRequest(int id, String client, String title, String artist, String date, String time,
                      int duration, int targetAudience, String type, String category) {
        this.id = id;
        this.client = client;
        this.title = title;
        this.artist = artist;
        this.date = date;
        this.time = time;
        this.duration = duration;
        this.targetAudience = targetAudience;
        this.type = type;
        this.category = category;
    }


    public int getId() {
        return id;
    }

    public String getClient() {
        return client;
    }

    public String getTitle() {
        return title;
    }

    public String getArtist() {
        return artist;
    }

    public String getDate() {
        return date;
    }

    public String getTime() {
        return time;
    }

    public int getDuration() {
        return duration;
    }

    public int getTargetAudience() {
        return targetAudience;
    }

    public String getType() {
        return type;
    }

    public String getCategory() {
        return category;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "JobRequest{" +
                "id=" + id +
                ", client='" + client + '\'' +
                ", title='" + title + '\'' +
                ", artist='" + artist + '\'' +
                ", date='" + date + '\'' +
                ", time='" + time + '\'' +
                ", duration=" + duration +
                ", targetAudience=" + targetAudience +
                ", type='" + type + '\'' +
                ", category='" + category + '\'' +
                '}';
    }
}