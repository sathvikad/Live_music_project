package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AutoMatchController {

    @FXML
    private TableView<MatchedRequest> matchedRequestTable;
    @FXML
    private TableColumn<MatchedRequest, String> eventNameColumn;
    @FXML
    private TableColumn<MatchedRequest, String> venueNameColumn;
    @FXML
    private TableColumn<MatchedRequest, String> statusColumn;

    private Connection conn = DatabaseInitializer.getConnection();

    @FXML
    public void initialize() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        venueNameColumn.setCellValueFactory(new PropertyValueFactory<>("venueName"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        ObservableList<MatchedRequest> matchedRequests = FXCollections.observableArrayList(autoMatch());
        matchedRequestTable.setItems(matchedRequests);
    }

    private List<MatchedRequest> autoMatch() {
        List<MatchedRequest> matchedList = new ArrayList<>();
        try {
            Statement stmt = conn.createStatement();

            // Get all requests
            String requestQuery = "SELECT * FROM requests";
            ResultSet requestSet = stmt.executeQuery(requestQuery);

            while (requestSet.next()) {
                String eventName = requestSet.getString("eventName");
                int expectedAttendance = requestSet.getInt("expectedAttendance");
                String eventType = requestSet.getString("eventType");
                String eventDate = requestSet.getString("eventDate");

                // Find venues matching the request
                String venueQuery = "SELECT * FROM venues WHERE capacity >= ? AND eventType = ? AND availability = 1";
                try (PreparedStatement venueStmt = conn.prepareStatement(venueQuery)) {
                    venueStmt.setInt(1, expectedAttendance);
                    venueStmt.setString(2, eventType);

                    ResultSet venueSet = venueStmt.executeQuery();
                    boolean matchFound = false;

                    while (venueSet.next()) {
                        String venueName = venueSet.getString("name");
                        matchFound = true;
                        matchedList.add(new MatchedRequest(eventName, venueName, "Matched"));
                    }

                    if (!matchFound) {
                        matchedList.add(new MatchedRequest(eventName, "No Match", "No suitable venue found"));
                    }
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return matchedList;
    }

    @FXML
    private void handleBack() {
        SceneSwitcher.switchScene("/streaming/live_music/managerDashboard.fxml");
    }
}
