package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class ManagerDashboardController {

    @FXML
    private void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/venueList.fxml");
    }

    @FXML
    private void handleAddVenue(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/addVenue.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/login.fxml");
    }
}
