package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class AutoMatchController {

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
