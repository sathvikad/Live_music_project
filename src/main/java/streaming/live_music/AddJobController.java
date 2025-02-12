package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Button;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;

public class AddJobController {

    @FXML private TextField clientField;
    @FXML private TextField titleField;
    @FXML private TextField artistField;
    @FXML private TextField dateField;
    @FXML private TextField timeField;
    @FXML private TextField durationField;
    @FXML private TextField targetAudienceField;
    @FXML private TextField typeField;
    @FXML private TextField categoryField;
    @FXML private Button submitButton;
    @FXML private Button backButton;  // Added

    @FXML
    private void handleSubmit(ActionEvent event) {
        String client = clientField.getText();
        String title = titleField.getText();
        String artist = artistField.getText();
        String date = dateField.getText();
        String time = timeField.getText();
        int duration = Integer.parseInt(durationField.getText());
        int targetAudience = Integer.parseInt(targetAudienceField.getText());
        String type = typeField.getText();
        String category = categoryField.getText();

        JobRequest newJob = new JobRequest(client, title, artist, date, time, duration, targetAudience, type, category);
        JobRequestDAO.addJobRequest(newJob);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Success");
        alert.setHeaderText(null);
        alert.setContentText("Job request added successfully!");
        alert.showAndWait();

        SceneSwitcher.switchScene((Button) event.getSource(), "ManagerDashboard.fxml");
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "ManagerDashboard.fxml");
    }
}
