package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    private TextField searchField;

    private ObservableList<Venue> venueList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Set up columns
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        capacityColumn.setCellValueFactory(new PropertyValueFactory<>("capacity"));
        suitableForColumn.setCellValueFactory(new PropertyValueFactory<>("suitableFor"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        // Load venues into the list
        loadVenues();

        // Wrap the list in a FilteredList for dynamic filtering
        FilteredList<Venue> filteredData = new FilteredList<>(venueList, b -> true);

        // Set the filter predicate whenever the search field changes
        searchField.textProperty().addListener((observable, oldValue, newValue) -> {
            filteredData.setPredicate(venue -> {
                // If filter text is empty, display all venues
                if (newValue == null || newValue.isEmpty()) {
                    return true;
                }

                String lowerCaseFilter = newValue.toLowerCase();

                // Filter based on name, capacity, or category
                if (venue.getName().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else if (String.valueOf(venue.getCapacity()).contains(lowerCaseFilter)) {
                    return true;
                } else if (venue.getCategory().toLowerCase().contains(lowerCaseFilter)) {
                    return true;
                } else {
                    return false; // Does not match
                }
            });
        });

        // Bind the filtered data to the table
        venueTable.setItems(filteredData);
    }

    private void loadVenues() {
        venueList.clear(); // Clear existing data to avoid duplication
        try (Connection conn = DatabaseInitializer.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM venues")) {

            while (rs.next()) {
                venueList.add(new Venue(
                        rs.getString("name"),
                        rs.getInt("capacity"),
                        rs.getString("suitable_for"),
                        rs.getString("category")
                ));
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene("managerDashboard.fxml");
    }
}
