package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class HelloController {
    @FXML
    private void handleGoToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}