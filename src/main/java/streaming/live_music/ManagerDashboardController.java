package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class ManagerDashboardController {

    @FXML
    private void handleViewVenues(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "VenueList.fxml");
    }

    @FXML
    private void handleAddJobRequest(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "AddJob.fxml");
    }

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "JobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "AutoMatch.fxml");
    }

    @FXML
    private void handleViewBookings(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "Booking.fxml");  // Ensure Booking.fxml exists
    }

    @FXML
    private void handleViewPieChart(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "PieChart.fxml");  // Ensure PieChart.fxml exists
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "Login.fxml");
    }
}
