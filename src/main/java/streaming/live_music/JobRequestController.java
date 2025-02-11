package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;

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

    private ObservableList<JobRequest> jobRequests = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        eventNameColumn.setCellValueFactory(new PropertyValueFactory<>("eventName"));
        eventDateColumn.setCellValueFactory(new PropertyValueFactory<>("eventDate"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("clientName"));
        expectedAttendanceColumn.setCellValueFactory(new PropertyValueFactory<>("expectedAttendance"));

        loadJobRequests();
        jobRequestTable.setItems(jobRequests);
    }

    private void loadJobRequests() {
        jobRequests.add(new JobRequest("Client A", "Event A", "2025-02-15", 100));
        jobRequests.add(new JobRequest("Client B", "Event B", "2025-03-10", 150));
        // If using a database or file, replace this with data loading logic
    }

    @FXML
    private void handleBackToMainMenu() {
        SceneSwitcher.switchScene("/streaming/live_music/managerDashboard.fxml");
    }
}
