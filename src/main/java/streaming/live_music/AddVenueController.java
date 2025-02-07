package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

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

    private final VenueDAO venueDAO = new VenueDAO();

    @FXML
    public void handleAddVenue() {
        String name = nameField.getText().trim();
        String location = locationField.getText().trim();
        String eventType = eventTypeField.getText().trim();
        String capacityText = capacityField.getText().trim();

        if (name.isEmpty() || location.isEmpty() || capacityText.isEmpty() || eventType.isEmpty()) {
            showAlert("Input Error", "All fields are required.");
            return;
        }

        try {
            int capacity = Integer.parseInt(capacityText);
            if (capacity <= 0) {
                throw new NumberFormatException();
            }

            Venue newVenue = new Venue(name, location, capacity, eventType);
            if (venueDAO.addVenue(newVenue)) {
                statusLabel.setText("Venue added successfully.");
                clearFields();
            } else {
                showAlert("Database Error", "Failed to add venue.");
            }
        } catch (NumberFormatException e) {
            showAlert("Input Error", "Capacity must be a positive number.");
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/managerDashboard.fxml");
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
