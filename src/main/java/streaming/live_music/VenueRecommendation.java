package streaming.live_music;

public class VenueRecommendation {
    private String venueName;
    private int matchScore;
    private int capacity;
    private String location;
    private String eventType;

    public VenueRecommendation(String venueName, int matchScore, int capacity, String location, String eventType) {
        this.venueName = venueName;
        this.matchScore = matchScore;
        this.capacity = capacity;
        this.location = location;
        this.eventType = eventType;
    }

    public String getVenueName() {
        return venueName;
    }

    public int getMatchScore() {
        return matchScore;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getLocation() {
        return location;
    }

    public String getEventType() {
        return eventType;
    }

    @Override
    public String toString() {
        return "VenueRecommendation{" +
                "venueName='" + venueName + '\'' +
                ", matchScore=" + matchScore +
                ", capacity=" + capacity +
                ", location='" + location + '\'' +
                ", eventType='" + eventType + '\'' +
                '}';
    }
}
