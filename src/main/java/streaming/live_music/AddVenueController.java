package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import javafx.scene.Node;
import javafx.event.ActionEvent;

public class AddVenueController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField locationField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField eventTypeField;
    @FXML
    private Label statusLabel;

    private VenueDAO venueDAO = new VenueDAO();  // DAO for database operations

    @FXML
    public void handleAddVenue() {
        try {
            String name = nameField.getText().trim();
            String location = locationField.getText().trim();
            String eventType = eventTypeField.getText().trim();
            String capacityText = capacityField.getText().trim();

            // Validate input fields
            if (name.isEmpty() || location.isEmpty() || capacityText.isEmpty() || eventType.isEmpty()) {
                showAlert("Input Error", "All fields are required. Please fill out all fields.");
                return;
            }

            // Validate capacity
            int capacity;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    throw new NumberFormatException();
                }
            } catch (NumberFormatException e) {
                showAlert("Input Error", "Capacity must be a positive number.");
                return;
            }

            Venue newVenue = new Venue(name, location, capacity, eventType);

            // Add venue to database using VenueDAO
            boolean success = venueDAO.addVenue(newVenue);
            if (success) {
                statusLabel.setText("Venue added successfully.");
                clearFields();  // Clear fields after successful addition
            } else {
                showAlert("Database Error", "Failed to add venue to the database.");
            }

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred: " + e.getMessage());
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
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
        nameField.clear();
        locationField.clear();
        capacityField.clear();
        eventTypeField.clear();
    }
}
