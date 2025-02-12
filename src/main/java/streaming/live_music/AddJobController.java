package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import javafx.collections.FXCollections;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class AddJobController {

    @FXML
    private TextField clientField;
    @FXML
    private TextField eventTitleField;
    @FXML
    private TextField artistField;
    @FXML
    private TextField dateField;
    @FXML
    private TextField timeField;
    @FXML
    private TextField durationField;
    @FXML
    private TextField targetAudienceField;
    @FXML
    private ComboBox<String> typeComboBox;

    @FXML
    public void initialize() {

        typeComboBox.setItems(FXCollections.observableArrayList(
                "Concert", "Festival", "Gig", "Corporate Event"
        ));
    }

    @FXML
    public void handleAddJob(ActionEvent event) {
        String client = clientField.getText().trim();
        String eventTitle = eventTitleField.getText().trim();
        String artist = artistField.getText().trim();
        String date = dateField.getText().trim();
        String time = timeField.getText().trim();
        String durationText = durationField.getText().trim();
        String targetAudienceText = targetAudienceField.getText().trim();
        String type = typeComboBox.getValue();

        if (client.isEmpty() || eventTitle.isEmpty() || artist.isEmpty() || date.isEmpty() ||
                time.isEmpty() || durationText.isEmpty() || targetAudienceText.isEmpty() || type == null) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        int duration;
        int targetAudience;

        try {
            duration = Integer.parseInt(durationText);
            targetAudience = Integer.parseInt(targetAudienceText);
        } catch (NumberFormatException e) {
            showAlert("Error", "Duration and Target Audience must be numbers.");
            return;
        }


        JobRequest newJob = new JobRequest(
                0,                // Job ID (assuming it's auto-generated in the database)
                client,            // Client
                eventTitle,        // Event Title
                artist,            // Artist
                date,              // Date
                time,              // Time
                duration,          // Duration (int)
                targetAudience,    // Target Audience (int)
                type,              // Type
                "Pending"          // Default Status
        );


        if (insertJobIntoDatabase(newJob)) {
            showAlert("Success", "Job request added successfully!");
            SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
        } else {
            showAlert("Error", "Failed to add job request.");
        }
    }

    private boolean insertJobIntoDatabase(JobRequest job) {
        String sql = "INSERT INTO job_requests (client, eventTitle, artist, date, time, duration, targetAudience, type, status) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.setString(1, job.getClient());
            stmt.setString(2, job.getEventTitle());
            stmt.setString(3, job.getArtist());
            stmt.setString(4, job.getDate());
            stmt.setString(5, job.getTime());
            stmt.setInt(6, job.getDuration());
            stmt.setInt(7, job.getTargetAudience());
            stmt.setString(8, job.getType());
            stmt.setString(9, job.getStatus());

            int rowsInserted = stmt.executeUpdate();
            return rowsInserted > 0;

        } catch (SQLException e) {
            System.err.println("Error inserting job request into database: " + e.getMessage());
            return false;
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
