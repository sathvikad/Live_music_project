package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class AutoMatchController {

    @FXML
    private Button matchButton;
    @FXML
    private Label resultLabel;

    @FXML
    private void handleAutoMatch() {
        String result = AutoMatchService.autoMatch(); // FIXED: Calls correct method
        resultLabel.setText(result);
    }

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene(matchButton, "ManagerDashboard.fxml");
    }
}
