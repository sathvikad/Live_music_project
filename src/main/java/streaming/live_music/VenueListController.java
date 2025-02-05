package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

        // Populate table with data from DataStore
        ObservableList<Venue> venues = FXCollections.observableArrayList(DataStore.venues);
        venueTable.setItems(venues);
    }
}
