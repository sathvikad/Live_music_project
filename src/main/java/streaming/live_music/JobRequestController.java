package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class JobRequestController {

    @FXML
    private TableView<JobRequest> jobRequestTable;

    @FXML
    private TableColumn<JobRequest, String> eventNameColumn;

    @FXML
    private TableColumn<JobRequest, String> eventDateColumn;

    @FXML
    private TableColumn<JobRequest, String> clientColumn;

    @FXML
    private TableColumn<JobRequest, Integer> expectedAttendanceColumn;

    private final ObservableList<JobRequest> jobRequests = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        // Map table columns to properties in JobRequest
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        expectedAttendanceColumn.setCellValueFactory(new PropertyValueFactory<>("expectedAttendance"));

        loadJobRequests();  // Load job requests into the table
    }

    private void loadJobRequests() {
        jobRequests.clear();  // Clear any existing data before loading

        try (Connection conn = DatabaseInitializer.getConnection()) {
            if (conn == null) {
                System.err.println("Database connection is null.");
                return;
            }

            String query = "SELECT client_name, event_name, event_date, expected_attendance FROM requests";
            try (PreparedStatement stmt = conn.prepareStatement(query);
                 ResultSet rs = stmt.executeQuery()) {

                while (rs.next()) {
                    String clientName = rs.getString("client_name");
                    String eventName = rs.getString("event_name");
                    String eventDate = rs.getString("event_date");
                    int expectedAttendance = rs.getInt("expected_attendance");

                    jobRequests.add(new JobRequest(eventName, eventDate, clientName, expectedAttendance));
                }

                jobRequestTable.setItems(jobRequests);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToMainMenu() {
        SceneSwitcher.switchScene("managerDashboard.fxml");
    }
}
