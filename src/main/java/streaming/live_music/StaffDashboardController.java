package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class StaffDashboardController {

    @FXML
    public void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/venueList.fxml");
    }

    @FXML
    public void handleLogout(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/login.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
