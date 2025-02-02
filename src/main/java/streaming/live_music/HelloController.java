package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class HelloController {

    @FXML
    private void handleJobRequests(ActionEvent event) {
        showInfo("Navigating to Job Requests...");
    }

    @FXML
    private void handleVenues(ActionEvent event) {
        showInfo("Navigating to Venue List...");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        showInfo("Navigating to Auto-Match Venues...");
    }

    @FXML
    private void handleOrderSummary(ActionEvent event) {
        showInfo("Navigating to Order Summary...");
    }

    private void showInfo(String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Navigation");
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
