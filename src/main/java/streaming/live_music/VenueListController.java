package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
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

    @FXML
    private TextField searchField;

    private ObservableList<Venue> venueList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        loadVenues();

        FilteredList<Venue> filteredVenues = new FilteredList<>(venueList, p -> true);
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredVenues.setPredicate(venue -> {
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }
                String lowerCaseFilter = newValue.toLowerCase();
                return venue.getName().toLowerCase().contains(lowerCaseFilter);
            });
        });

        venueTable.setItems(filteredVenues);
    }

    private void loadVenues() {
        venueList.addAll(new VenueDAO().getAllVenues());
    }

    public void refreshVenueList() {
        venueList.clear();
        venueList.addAll(new VenueDAO().getAllVenues());
    }

    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene("/streaming/live_music/managerDashboard.fxml");
    }
}
