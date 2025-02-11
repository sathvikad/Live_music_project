package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class OrderSummaryController {

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene("/streaming/live_music/managerDashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
