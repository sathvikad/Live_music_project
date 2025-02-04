
package streaming.live_music;

public class VenueRecommendation {
    private String venueName;
    private int matchScore;
    private int capacity;

    public VenueRecommendation(String venueName, int matchScore, int capacity) {
        this.venueName = venueName;
        this.matchScore = matchScore;
        this.capacity = capacity;
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
}
