package streaming.live_music;

import javafx.fxml.FXML;

public class ManagerDashboardController {

    @FXML
    private void handleViewVenues() {
        SceneSwitcher.switchScene("/streaming/live_music/venueList.fxml");
    }

    @FXML
    private void handleAddVenue() {
        SceneSwitcher.switchScene("/streaming/live_music/addVenue.fxml");
    }

    @FXML
    private void handleJobRequests() {
        SceneSwitcher.switchScene("/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch() {
        SceneSwitcher.switchScene("/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void handleLogout() {
        SceneSwitcher.switchScene("/streaming/live_music/login.fxml");
    }
}
