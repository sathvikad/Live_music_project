package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class AutoMatchController {

    @FXML
    private TextArea matchResultsArea;

    @FXML
    public void handleStartMatching() {
        StringBuilder results = new StringBuilder();
        for (JobRequest jobRequest : DataStore.jobRequests) {
            boolean matchFound = false;
            for (Venue venue : DataStore.venues) {
                if (venue.getLocation().equalsIgnoreCase(jobRequest.getPreferredLocation()) &&
                        venue.getCapacity() >= jobRequest.getExpectedAttendees()) {
                    results.append("Matched Event: ").append(jobRequest.getEventName())
                            .append(" at Venue: ").append(venue.getName())
                            .append(" [Location: ").append(venue.getLocation())
                            .append(", Capacity: ").append(venue.getCapacity()).append("]\n");
                    matchFound = true;
                    break;
                }
            }
            if (!matchFound) {
                results.append("No match found for Event: ").append(jobRequest.getEventName()).append("\n");
            }
        }
        matchResultsArea.setText(results.toString());
    }

    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene((Stage) matchResultsArea.getScene().getWindow(), "/streaming/live_music/managerDashboard.fxml");
    }
}
