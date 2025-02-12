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

public class JobRequestController {

    @FXML
    private TableView<JobRequest> jobTable;
    @FXML
    private TableColumn<JobRequest, String> clientColumn;
    @FXML
    private TableColumn<JobRequest, String> eventTitleColumn;
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
    private TextField searchField;

    private ObservableList<JobRequest> jobList = FXCollections.observableArrayList();

    @FXML
    public void initialize() {
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        eventTitleColumn.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        artistColumn.setCellValueFactory(new PropertyValueFactory<>("artist"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        durationColumn.setCellValueFactory(new PropertyValueFactory<>("duration"));
        targetAudienceColumn.setCellValueFactory(new PropertyValueFactory<>("targetAudience"));
        typeColumn.setCellValueFactory(new PropertyValueFactory<>("type"));

        refreshJobTable();
    }

    private void refreshJobTable() {
        jobList.setAll(JobRequestDAO.getAllJobRequests());
        jobTable.setItems(jobList);
    }

    @FXML
    private void handleSearch() {
        String query = searchField.getText().trim().toLowerCase();
        if (query.isEmpty()) {
            jobTable.setItems(jobList);
        } else {
            ObservableList<JobRequest> filteredList = FXCollections.observableArrayList();
            for (JobRequest job : jobList) {
                if (job.getClient().toLowerCase().contains(query) ||
                        job.getEventTitle().toLowerCase().contains(query) ||
                        job.getArtist().toLowerCase().contains(query) ||
                        job.getType().toLowerCase().contains(query)) {
                    filteredList.add(job);
                }
            }
            jobTable.setItems(filteredList);
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
