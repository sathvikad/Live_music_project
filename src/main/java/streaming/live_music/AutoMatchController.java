package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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
                        venue.getCapacity() >= jobRequest.getExpectedAttendees() &&
                        venue.getEventType().equalsIgnoreCase(jobRequest.getEventName())) {

                    results.append("Match Found:\n")
                            .append("Event Name: ").append(jobRequest.getEventName()).append("\n")
                            .append("Venue Name: ").append(venue.getName()).append("\n")
                            .append("Location: ").append(venue.getLocation()).append("\n")
                            .append("Capacity: ").append(venue.getCapacity()).append("\n")
                            .append("Event Type: ").append(venue.getEventType()).append("\n\n");
                    matchFound = true;
                }
            }

            if (!matchFound) {
                results.append("No match found for Event: ").append(jobRequest.getEventName()).append("\n\n");
            }
        }

        // Display results
        if (results.length() > 0) {
            matchResultsArea.setText(results.toString());
        } else {
            showAlert("No Matches", "No venues matched the job requests.");
        }
    }

    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene((Stage) matchResultsArea.getScene().getWindow(), "/streaming/live_music/managerDashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
