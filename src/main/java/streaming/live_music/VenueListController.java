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
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        loadVenueData();
    }

    private void loadVenueData() {
        ObservableList<Venue> venues = FXCollections.observableArrayList(DataStore.venues);
        venueTable.setItems(venues);
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene((Stage) ((Node) event.getSource()).getScene().getWindow(), "/streaming/live_music/managerDashboard.fxml");
    }
}
