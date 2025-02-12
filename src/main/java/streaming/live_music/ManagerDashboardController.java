package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;

public class ManagerDashboardController {

    @FXML private Button autoMatchButton;

    @FXML
    private void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "VenueList.fxml");
    }

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "JobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) { // NEW FUNCTION
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "AutoMatch.fxml");
    }

    @FXML
    private void handleViewBookings(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "Booking.fxml");
    }

    @FXML
    private void handleViewPieChart(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "PieChart.fxml");
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "Login.fxml");
    }
}
