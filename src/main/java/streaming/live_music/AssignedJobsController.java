package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AssignedJobsController {

    @FXML
    private TableView<JobRequest> assignedJobsTable;
    @FXML
    private TableColumn<JobRequest, Integer> jobIdColumn;
    @FXML
    private TableColumn<JobRequest, String> eventTitleColumn;
    @FXML
    private TableColumn<JobRequest, String> clientColumn;
    @FXML
    private TableColumn<JobRequest, String> dateColumn;
    @FXML
    private TableColumn<JobRequest, String> timeColumn;
    @FXML
    private TableColumn<JobRequest, String> statusColumn;

    private ObservableList<JobRequest> assignedJobsList = FXCollections.observableArrayList();
    private String loggedInUsername;

    public void setUser(String username) {
        this.loggedInUsername = username;
        loadAssignedJobs();
    }

    @FXML
    public void initialize() {
        jobIdColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        eventTitleColumn.setCellValueFactory(new PropertyValueFactory<>("eventTitle"));
        clientColumn.setCellValueFactory(new PropertyValueFactory<>("client"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        timeColumn.setCellValueFactory(new PropertyValueFactory<>("time"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));

        if (loggedInUsername != null) {
            loadAssignedJobs();
        }
    }

    private void loadAssignedJobs() {
        if (loggedInUsername == null) {
            System.err.println("Error: No logged-in user!");
            return;
        }

        String query = "SELECT * FROM job_requests WHERE assigned_staff = ?";

        try (Connection conn = DatabaseInitializer.getConnection()) {
            if (conn == null) {
                System.err.println("Error: Database connection is NULL!");
                return;
            }
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, loggedInUsername);
            ResultSet rs = stmt.executeQuery();

            assignedJobsList.clear();
            while (rs.next()) {
                assignedJobsList.add(new JobRequest(
                        rs.getInt("id"),
                        rs.getString("eventTitle"),
                        rs.getString("client"),
                        rs.getString("date"),
                        rs.getString("time"),
                        rs.getString("status")
                ));
            }

            assignedJobsTable.setItems(assignedJobsList);

        } catch (SQLException e) {
            System.err.println("Database error while loading assigned jobs: " + e.getMessage());
        }
    }

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "StaffDashboard.fxml");
    }
}
