package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class AutoMatchController {

    @FXML
    private TextArea matchResultsArea;

    @FXML
    public void handleStartMatching() {
        StringBuilder results = new StringBuilder();
        List<VenueRecommendation> recommendations = new ArrayList<>();

        for (JobRequest jobRequest : DataStore.jobRequests) {
            boolean matchFound = false;

            for (Venue venue : DataStore.venues) {
                if (venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation()) &&
                        venue.getCapacity() >= jobRequest.getExpectedAttendees() &&
                        venue.getEventType().equalsIgnoreCase(jobRequest.getEventName())) {

                    int matchScore = calculateMatchScore(venue, jobRequest);  // Optional scoring logic
                    recommendations.add(new VenueRecommendation(
                            venue.getName(),
                            matchScore,
                            venue.getCapacity(),
                            venue.getLocation(),
                            venue.getEventType()
                    ));
                    matchFound = true;
                }
            }

            if (matchFound) {
                for (VenueRecommendation recommendation : recommendations) {
                    results.append("Matched Venue Recommendation:\n")
                            .append("---------------------------------\n")
                            .append("Event Name: ").append(jobRequest.getEventName()).append("\n")
                            .append("Venue Name: ").append(recommendation.getVenueName()).append("\n")
                            .append("Location: ").append(recommendation.getLocation()).append("\n")
                            .append("Capacity: ").append(recommendation.getCapacity()).append("\n")
                            .append("Event Type: ").append(recommendation.getEventType()).append("\n")
                            .append("Match Score: ").append(recommendation.getMatchScore()).append("%\n")
                            .append("---------------------------------\n\n");
                }
            } else {
                results.append("No match found for Event: ").append(jobRequest.getEventName()).append("\n\n");
            }
        }

        matchResultsArea.setText(results.toString());
    }

    private int calculateMatchScore(Venue venue, JobRequest jobRequest) {
        // Example scoring logic (expand based on criteria)
        int score = 0;
        if (venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation())) {
            score += 50;  // Location match weight
        }
        if (venue.getEventType().equalsIgnoreCase(jobRequest.getEventName())) {
            score += 30;  // Event type match weight
        }
        if (venue.getCapacity() >= jobRequest.getExpectedAttendees()) {
            score += 20;  // Capacity match weight
        }
        return score;
    }
}
