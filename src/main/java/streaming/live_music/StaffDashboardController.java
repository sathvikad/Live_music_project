package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;

public class StaffDashboardController {

    @FXML
    private void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "venueList.fxml");
    }

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "jobRequest.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "login.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
