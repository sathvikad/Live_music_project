package streaming.live_music;

public class JobRequest {
    private String client;
    private String title;
    private String artist;
    private String date;
    private String time;
    private int duration;
    private int targetAudience;
    private String type;
    private String category;

    public JobRequest(String client, String title, String artist, String date, String time, int duration, int targetAudience, String type, String category) {
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

    // Getters and Setters
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getArtist() {
        return artist;
    }

    public void setArtist(String artist) {
        this.artist = artist;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public int getDuration() {
        return duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public int getTargetAudience() {
        return targetAudience;
    }

    public void setTargetAudience(int targetAudience) {
        this.targetAudience = targetAudience;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }
}
