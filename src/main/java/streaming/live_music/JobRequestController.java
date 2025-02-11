package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;

public class JobRequestController {

    @FXML
    private TableView<JobRequest> jobRequestTable;
    @FXML
    private TableColumn<JobRequest, String> clientColumn;
    @FXML
    private TableColumn<JobRequest, String> titleColumn;
    @FXML
    private TableColumn<JobRequest, String> artistColumn;
    @FXML
    private TableColumn<JobRequest, String> dateColumn;
    @FXML
    private TableColumn<JobRequest, String> timeColumn;
    @FXML
    private TableColumn<JobRequest, Integer> durationColumn;
    @FXML
    private TableColumn<JobRequest, Integer> targetAudienceColumn;
    @FXML
    private TableColumn<JobRequest, String> typeColumn;
    @FXML
    private TableColumn<JobRequest, String> categoryColumn;

    @FXML
    public void initialize() {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        targetAudienceColumn.setCellValueFactory(new PropertyValueFactory<>("targetAudience"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));
        categoryColumn.setCellValueFactory(new PropertyValueFactory<>("category"));

        loadJobRequests();
    }

    private void loadJobRequests() {
        ObservableList<JobRequest> jobRequests = FXCollections.observableArrayList(JobRequestDAO.getAllJobRequests());
        jobRequestTable.setItems(jobRequests);
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
