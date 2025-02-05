package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.control.Label;

public class AddVenueController {

    @FXML
    private TextField nameField;

    @FXML
    private TextField locationField;

    @FXML
    private TextField capacityField;

    @FXML
    private Label statusLabel;

    @FXML
    public void handleAddVenue() {
        try {
            String name = nameField.getText();
            String location = locationField.getText();
            int capacity = Integer.parseInt(capacityField.getText());  // Convert String to int

            Venue newVenue = new Venue(name, location, capacity);
            DataStore.venues.add(newVenue);
            statusLabel.setText("Venue added successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid capacity. Please enter a valid number.");
        }
    }
}
