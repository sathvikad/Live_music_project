package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;

public class AutoMatchController {

    @FXML
    private TextArea matchResultsArea;

    public void handleStartMatching() {
        StringBuilder results = new StringBuilder();
        JobRequestDAO jobRequestDAO = new JobRequestDAO();
        VenueDAO venueDAO = new VenueDAO();

        List<JobRequest> jobRequests = jobRequestDAO.getAllJobRequests();
        List<Venue> venues = venueDAO.getAllVenues();

        for (JobRequest jobRequest : jobRequests) {
            List<VenueRecommendation> recommendations = new ArrayList<>();

            for (Venue venue : venues) {
                if (venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation()) &&
                        venue.getCapacity() >= jobRequest.getExpectedAttendees() &&
                        (venue.getEventType().equalsIgnoreCase(jobRequest.getEventName()) ||
                                venue.getEventType().contains(jobRequest.getEventName()))) {

                    int matchScore = calculateMatchScore(venue, jobRequest);
                    recommendations.add(new VenueRecommendation(
                            venue.getName(),
                            matchScore,
                            venue.getCapacity(),
                            venue.getLocation(),
                            venue.getEventType()
                    ));
                }
            }

            appendRecommendations(results, jobRequest, recommendations);
        }

        matchResultsArea.setText(results.toString());
    }

    private void appendRecommendations(StringBuilder results, JobRequest jobRequest, List<VenueRecommendation> recommendations) {
        if (recommendations.isEmpty()) {
            results.append("No match found for Event: ").append(jobRequest.getEventName()).append("\n\n");
        } else {
            results.append("Matches for Event: ").append(jobRequest.getEventName()).append("\n");
            recommendations.sort((v1, v2) -> Integer.compare(v2.getMatchScore(), v1.getMatchScore()));

            for (VenueRecommendation recommendation : recommendations) {
                results.append("Venue Name: ").append(recommendation.getVenueName()).append("\n")
                        .append("Location: ").append(recommendation.getLocation()).append("\n")
                        .append("Capacity: ").append(recommendation.getCapacity()).append("\n")
                        .append("Match Score: ").append(recommendation.getMatchScore()).append("%\n")
                        .append("-------------------------------------------\n");
            }
            results.append("\n");
        }
    }

    private int calculateMatchScore(Venue venue, JobRequest jobRequest) {
        int score = 50;
        if (venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation())) {
            score += 30;
        }
        if (venue.getCapacity() >= jobRequest.getExpectedAttendees()) {
            score += 20;
        }
        return score;
    }
}
