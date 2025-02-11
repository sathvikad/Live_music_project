package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;

public class ManagerDashboardController {
    @FXML
    private void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "VenueList.fxml");
    }

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "JobRequests.fxml");
    }

    @FXML
    private void handleAddVenue(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "AddVenue.fxml");
    }

    @FXML
    private void handleVenueUtilization(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "VenueUtilization.fxml");
    }
}
