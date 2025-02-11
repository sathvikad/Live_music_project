package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddVenueController {

    @FXML
    private TextField venueNameField;

    @FXML
    private TextField venueLocationField;

    @FXML
    private TextField venueCapacityField;

    private final VenueDAO venueDAO = new VenueDAO();

    @FXML
    private void handleAddVenue() {
        String name = venueNameField.getText();
        String location = venueLocationField.getText();
        int capacity;

        try {
            capacity = Integer.parseInt(venueCapacityField.getText());
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid capacity. Please enter a valid number.");
            return;
        }

        if (name.isEmpty() || location.isEmpty()) {
            showAlert("Error", "Please fill in all fields.");
            return;
        }

        Venue newVenue = new Venue(name, location, capacity);
        venueDAO.addVenue(newVenue);
        showAlert("Success", "Venue added successfully.");

        clearFields();
    }

    private void clearFields() {
        venueNameField.clear();
        venueLocationField.clear();
        venueCapacityField.clear();
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene("/streaming/live_music/managerDashboard.fxml");
    }
}
