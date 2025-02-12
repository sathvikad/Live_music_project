package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class VenueListController {

    @FXML
    private TableView<Venue> venueTable;
    @FXML
    private TableColumn<Venue, String> nameColumn;
    @FXML
    private TableColumn<Venue, Integer> capacityColumn;
    @FXML
    private TableColumn<Venue, String> suitableForColumn;
    @FXML
    private TableColumn<Venue, String> categoryColumn;
    @FXML
    private TableColumn<Venue, Double> bookingPriceColumn;
    @FXML
    private TextField searchField;

    private ObservableList<Venue> venueList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        suitableForColumn.setCellValueFactory(new PropertyValueFactory<>("suitableFor"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("bookingPrice"));

        refreshVenueTable();
    }

    private void refreshVenueTable() {
        venueList.setAll(VenueDAO.getAllVenues());
        venueTable.setItems(venueList);
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            venueTable.setItems(venueList);
        } else {
            ObservableList<Venue> filteredList = FXCollections.observableArrayList();
            for (Venue venue : venueList) {
                if (venue.getName().toLowerCase().contains(query) ||
                        venue.getCategory().toLowerCase().contains(query) ||
                        venue.getSuitableFor().toLowerCase().contains(query)) {
                    filteredList.add(venue);
                }
            }
            venueTable.setItems(filteredList);
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
