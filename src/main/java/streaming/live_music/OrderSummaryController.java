package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class OrderSummaryController {

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}