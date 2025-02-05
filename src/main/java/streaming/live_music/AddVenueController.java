package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

public class AddVenueController {

    @FXML
    private TextField venueNameField;

    @FXML
    private TextField venueLocationField;

    @FXML
    private TextField venueCapacityField;

    @FXML
    private Label statusLabel;

    // Handles adding a new venue to the data store
    @FXML
    public void handleAddVenue() {
        try {
            String name = venueNameField.getText();
            String location = venueLocationField.getText();
            int capacity = Integer.parseInt(venueCapacityField.getText());

            if (name.isEmpty() || location.isEmpty()) {
                statusLabel.setText("Name and location cannot be empty.");
                return;
            }

            Venue newVenue = new Venue(name, location, capacity);
            DataStore.venues.add(newVenue);
            statusLabel.setText("Venue added successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid capacity. Please enter a valid number.");
        }
    }

    // Navigates back to the main dashboard
    @FXML
    public void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
    }
}
