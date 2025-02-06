package streaming.live_music;

import javafx.fxml.FXML;
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

    @FXML
    public void handleSubmitRequest() {
        try {
            String eventName = eventNameField.getText();
            String preferredLocation = preferredLocationField.getText();
            int expectedAttendees = Integer.parseInt(expectedAttendeesField.getText());

            JobRequest jobRequest = new JobRequest(eventName, preferredLocation, expectedAttendees);
            DataStore.addJobRequest(jobRequest);
            statusLabel.setText("Job request submitted successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid number of attendees. Please enter a valid number.");
        }
    }

    @FXML
    public void handleBackToMain() {
        SceneSwitcher.switchScene((Stage) eventNameField.getScene().getWindow(), "/streaming/live_music/managerDashboard.fxml");
    }
}
