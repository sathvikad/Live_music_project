package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

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
    public void initialize() {
        System.out.println("Initializing VenueListController...");

        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        suitableForColumn.setCellValueFactory(new PropertyValueFactory<>("suitableFor"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        bookingPriceColumn.setCellValueFactory(new PropertyValueFactory<>("bookingPrice"));

        refreshVenueTable();
    }

    private void refreshVenueTable() {
        System.out.println("Refreshing Venue Table...");
        ObservableList<Venue> venues = FXCollections.observableArrayList(VenueDAO.getAllVenues());
        venueTable.setItems(venues);

        if (venueTable.getItems().isEmpty()) {
            System.out.println("No venues available.");
        } else {
            System.out.println("Venues loaded successfully.");
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}