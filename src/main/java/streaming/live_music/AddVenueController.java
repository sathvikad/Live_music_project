package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;

public class AddVenueController {

    @FXML
    private TextField nameField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField suitableForField;
    @FXML
    private TextField categoryField;

    private final VenueDAO venueDAO = new VenueDAO(DatabaseInitializer.getConnection());

    @FXML
    private void handleAddVenue(ActionEvent event) {
        try {
            // Trim inputs and validate
            String name = nameField.getText().trim();
            String suitableFor = suitableForField.getText().trim();
            String category = categoryField.getText().trim();
            String capacityText = capacityField.getText().trim();

            if (name.isEmpty() || suitableFor.isEmpty() || category.isEmpty() || capacityText.isEmpty()) {
                showAlert("Invalid input", "All fields must be filled.");
                return;
            }

            int capacity;
            try {
                capacity = Integer.parseInt(capacityText);
                if (capacity <= 0) {
                    showAlert("Invalid Capacity", "Capacity must be a positive number.");
                    return;
                }
            } catch (NumberFormatException e) {
                showAlert("Invalid Capacity", "Please enter a valid numeric value for capacity.");
                return;
            }

            // Insert the new venue into the database
            Venue venue = new Venue(name, capacity, suitableFor, category);
            venueDAO.addVenue(venue);

            // Switch to the venue list scene and reload the data
            SceneSwitcher.switchScene("venueList.fxml");

        } catch (Exception e) {
            showAlert("Error", "An unexpected error occurred while adding the venue.");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene("managerDashboard.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setHeaderText(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
