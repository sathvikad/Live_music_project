package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.util.List;
import java.util.stream.Collectors;

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

    private ObservableList<Venue> venueList;

    private VenueDAO venueDAO = new VenueDAO();

    @FXML
    public void initialize() {
        // Bind columns to Venue properties
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        locationColumn.setCellValueFactory(new PropertyValueFactory<>("location"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));

        // Load data from database and display
        loadVenueData();
    }

    private void loadVenueData() {
        List<Venue> venues = venueDAO.getAllVenues();
        venueList = FXCollections.observableArrayList(venues);
        venueTable.setItems(venueList);
    }

    @FXML
    private void handleSearch() {
        String searchText = searchField.getText().toLowerCase().trim();
        if (searchText.isEmpty()) {
            venueTable.setItems(venueList);  // Reset to full list
        } else {
            List<Venue> filteredList = venueList.stream()
                    .filter(venue -> venue.getName().toLowerCase().contains(searchText))
                    .collect(Collectors.toList());
            venueTable.setItems(FXCollections.observableArrayList(filteredList));
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/managerDashboard.fxml");
    }
}
