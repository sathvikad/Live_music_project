package streaming.live_music;

import java.util.ArrayList;
import java.util.List;

public class VenueDAO {
    private static final List<Venue> venues = new ArrayList<>();

    public void addVenue(Venue venue) {
        venues.add(venue);
    }

    public List<Venue> getAllVenues() {
        return new ArrayList<>(venues);
    }
}
