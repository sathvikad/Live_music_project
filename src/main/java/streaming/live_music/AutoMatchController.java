package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMatchController {

    @FXML
    private TextArea matchResultsArea;

    private static final Logger LOGGER = Logger.getLogger(AutoMatchController.class.getName());

    /**
     * Initiates the venue matching process.
     */
    public void handleStartMatching() {
        LOGGER.log(Level.INFO, "Starting venue matching process...");

        StringBuilder results = new StringBuilder();
        JobRequestDAO jobRequestDAO = new JobRequestDAO();
        VenueDAO venueDAO = new VenueDAO();

        List<JobRequest> jobRequests = jobRequestDAO.getAllJobRequests();
        List<Venue> venues = venueDAO.getAllVenues();

        if (jobRequests.isEmpty()) {
            LOGGER.log(Level.WARNING, "No job requests available to match.");
            showAlert("No Job Requests", "There are no job requests available to match.");
            return;
        }

        if (venues.isEmpty()) {
            LOGGER.log(Level.WARNING, "No venues available to match.");
            showAlert("No Venues", "There are no venues available to match.");
            return;
        }

        LOGGER.log(Level.INFO, "Matching {0} job requests with {1} venues.", new Object[]{jobRequests.size(), venues.size()});

        for (JobRequest jobRequest : jobRequests) {
            List<VenueRecommendation> recommendations = new ArrayList<>();

            for (Venue venue : venues) {
                if (isVenueSuitable(venue, jobRequest)) {
                    int matchScore = calculateMatchScore(venue, jobRequest);
                    recommendations.add(new VenueRecommendation(
                            venue.getName(),
                            matchScore,
                            venue.getCapacity(),
                            venue.getLocation(),
                            venue.getEventType()
                    ));
                    LOGGER.log(Level.INFO, "Venue {0} matches with event {1} with a score of {2}%",
                            new Object[]{venue.getName(), jobRequest.getEventName(), matchScore});
                }
            }

            appendRecommendations(results, jobRequest, recommendations);
        }

        if (results.length() == 0) {
            results.append("No matches found.\n");
        }

        matchResultsArea.setText(results.toString());
        LOGGER.log(Level.INFO, "Venue matching process completed.");
    }

    private boolean isVenueSuitable(Venue venue, JobRequest jobRequest) {
        boolean locationMatch = venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation());
        boolean capacityMatch = venue.getCapacity() >= jobRequest.getExpectedAttendees();
        boolean eventTypeMatch = venue.getEventType().equalsIgnoreCase(jobRequest.getEventName())
                || venue.getEventType().toLowerCase().contains(jobRequest.getEventName().toLowerCase());

        LOGGER.log(Level.FINE, "Checking venue suitability: {0} (Location: {1}, Capacity: {2}) for Event: {3}",
                new Object[]{venue.getName(), locationMatch, capacityMatch, jobRequest.getEventName()});

        return locationMatch && capacityMatch && eventTypeMatch;
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
        } else if (venue.getCapacity() >= jobRequest.getExpectedAttendees() / 2) {
            score += 10;
        }

        if (venue.getEventType().toLowerCase().contains(jobRequest.getEventName().toLowerCase())) {
            score += 10;
        }

        return Math.min(score, 100);
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/managerDashboard.fxml");
    }
}
