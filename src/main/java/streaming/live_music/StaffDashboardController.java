package streaming.live_music;

import javafx.fxml.FXML;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class StaffDashboardController {

    @FXML
    private void handleViewAssignedJobs(ActionEvent event) {
        System.out.println("Navigating to Assigned Jobs...");
        SceneSwitcher.switchScene((Node) event.getSource(), "AssignedJobs.fxml"); // Ensure AssignedJobs.fxml exists
    }

    @FXML
    private void handleLogout(ActionEvent event) {
        System.out.println("Logging out...");
        SceneSwitcher.switchScene((Node) event.getSource(), "Login.fxml");
    }
}
