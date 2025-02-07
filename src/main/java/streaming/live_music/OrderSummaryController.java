package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;

public class OrderSummaryController {

    /**
     * Handles navigating back to the previous scene.
     */
    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/previousScene.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
