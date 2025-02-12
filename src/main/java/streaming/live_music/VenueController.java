package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class VenueController {
    @FXML
    private ListView<String> venueListView;
    @FXML
    private TextField searchField;

    @FXML
    public void initialize() {
        loadVenues("");
    }

    @FXML
    private void handleSearch() {
        loadVenues(searchField.getText().trim());
    }

    private void loadVenues(String searchQuery) {
        ObservableList<String> venues = FXCollections.observableArrayList(VenueDAO.getVenuesBySearch(searchQuery));
        venueListView.setItems(venues);
    }
}
