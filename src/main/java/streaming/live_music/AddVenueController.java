package streaming.live_music;

import javafx.fxml.FXML;
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

    @FXML
    public void handleAddVenue() {
        try {
            String name = nameField.getText();
            String location = locationField.getText();
            int capacity = Integer.parseInt(capacityField.getText());
            String eventType = eventTypeField.getText();

            Venue newVenue = new Venue(name, location, capacity, eventType);
            DataStore.addVenue(newVenue);
            statusLabel.setText("Venue added successfully.");
        } catch (NumberFormatException e) {
            statusLabel.setText("Invalid capacity. Please enter a valid number.");
        }
    }

    @FXML
    public void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
    }
}
