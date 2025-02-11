package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.event.ActionEvent;

public class VenueUtilizationController {

    @FXML
    private BarChart<?, ?> venueUtilizationChart;

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
