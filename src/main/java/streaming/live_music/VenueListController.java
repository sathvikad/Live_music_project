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

import java.util.List;

public class VenueListController {

    @FXML
    private TableView<Venue> venueTable;

    @FXML
    private TableColumn<Venue, String> nameColumn;

    @FXML
    private TableColumn<Venue, String> locationColumn;

    @FXML
    private TableColumn<Venue, Integer> capacityColumn;

    private VenueDAO venueDAO = new VenueDAO();

    @FXML
    public void initialize() {
        // Bind the table columns to the venue properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        // Load venue data from the database
        loadVenueData();
    }

    /**
     * Loads venue data from the database and sets it in the TableView.
     */
    private void loadVenueData() {
        List<Venue> venues = venueDAO.getAllVenues();
        if (!venues.isEmpty()) {
            ObservableList<Venue> venueObservableList = FXCollections.observableArrayList(venues);
            venueTable.setItems(venueObservableList);
        } else {
            System.out.println("No venues found in the database.");
        }
    }

    /**
     * Handles the "Back to Dashboard" button click.
     *
     * @param event The action event triggered by the button.
     */
    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
    }
}
