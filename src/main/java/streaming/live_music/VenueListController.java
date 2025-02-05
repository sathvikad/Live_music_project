package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;

public class VenueListController {

    @FXML
    private TableView<Venue> venueTable;

    @FXML
    private TableColumn<Venue, String> nameColumn;

    @FXML
    private TableColumn<Venue, String> locationColumn;

    @FXML
    private TableColumn<Venue, Integer> capacityColumn;

    public void initialize() {
        // Setting up column property mappings
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        // Load venue data and populate the table
        loadVenueData();
    }

    // Method to load venue data into the table
    private void loadVenueData() {
        if (DataStore.venues != null && !DataStore.venues.isEmpty()) {
            ObservableList<Venue> venues = FXCollections.observableArrayList(DataStore.venues);
            venueTable.setItems(venues);
        } else {
            // Display an empty message if no data is available
            venueTable.setPlaceholder(new javafx.scene.control.Label("No venues available."));
        }
    }

    // Handle navigation back to the manager dashboard
    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
    }
}
