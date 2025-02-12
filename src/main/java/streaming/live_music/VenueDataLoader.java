package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

public class VenueDataLoader {

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
    private TableColumn<Venue, Double> priceColumn;

    @FXML
    public void initialize() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        suitableForColumn.setCellValueFactory(new PropertyValueFactory<>("suitableFor"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));
        priceColumn.setCellValueFactory(new PropertyValueFactory<>("bookingPrice"));

        loadVenues();
    }

    private void loadVenues() {
        System.out.println("Loading venues from database...");
        ObservableList<Venue> venues = FXCollections.observableArrayList(VenueDAO.getAllVenues());
        venueTable.setItems(venues);

        if (venues.isEmpty()) {
            System.out.println("No venues found in database.");
        } else {
            System.out.println("Venues loaded successfully.");
        }
    }
}