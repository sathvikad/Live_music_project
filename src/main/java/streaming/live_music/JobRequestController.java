package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class JobRequestController {

    @FXML
    private TextField eventNameField;
    @FXML
    private TextField preferredLocationField;
    @FXML
    private TextField expectedAttendeesField;
    @FXML
    private Label statusLabel;

    private JobRequestDAO jobRequestDAO = new JobRequestDAO();  // DAO for database operations

    @FXML
    public void handleSubmitRequest() {
        try {
            String eventName = eventNameField.getText().trim();
            String preferredLocation = preferredLocationField.getText().trim();
            String attendeesText = expectedAttendeesField.getText().trim();

            // Validate fields
            if (eventName.isEmpty() || preferredLocation.isEmpty() || attendeesText.isEmpty()) {
                showAlert("Input Error", "All fields are required. Please fill out all fields.");
                return;
            }

            // Validate attendees
            int expectedAttendees;
            try {
                expectedAttendees = Integer.parseInt(attendeesText);
                if (expectedAttendees <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Expected attendees must be a positive number.");
                return;
            }

            // Create and add job request to database
            JobRequest jobRequest = new JobRequest(eventName, preferredLocation, expectedAttendees);
            boolean success = jobRequestDAO.addJobRequest(jobRequest);

            if (success) {
                showAlert("Success", "Job request submitted successfully.");
                clearFields();  // Clear fields after successful submission
            } else {
                showAlert("Database Error", "Failed to submit the job request.");
            }

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleBackToMain(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        eventNameField.clear();
        preferredLocationField.clear();
        expectedAttendeesField.clear();
    }
}
