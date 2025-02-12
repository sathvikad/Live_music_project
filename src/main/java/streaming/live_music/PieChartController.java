package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class PieChartController {

    @FXML
    private PieChart venueUtilizationChart;

    @FXML
    private Button backButton;

    @FXML
    public void initialize() {
        System.out.println("Initializing Pie Chart...");
        loadChartData();
    }

    private void loadChartData() {
        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList();

        String sql = "SELECT venue_name, COUNT(*) AS usage_count FROM bookings GROUP BY venue_name";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String venueName = rs.getString("venue_name");
                int usageCount = rs.getInt("usage_count");

                // Add data to the chart
                pieChartData.add(new PieChart.Data(venueName, usageCount));
            }

            // Update Pie Chart
            venueUtilizationChart.setData(pieChartData);

        } catch (SQLException e) {
            System.out.println("Error loading pie chart data: " + e.getMessage());
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene(backButton, "ManagerDashboard.fxml");
    }
}
