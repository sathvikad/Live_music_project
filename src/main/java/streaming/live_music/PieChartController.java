package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.PieChart;
import javafx.scene.layout.VBox;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class PieChartController {

    @FXML private PieChart venuePieChart;

    @FXML
    public void initialize() {
        ObservableList<PieChart.Data> chartData = FXCollections.observableArrayList();

        try (Connection conn = DatabaseInitializer.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT venue_name, COUNT(*) as count FROM bookings GROUP BY venue_name")) {

            while (rs.next()) {
                String venueName = rs.getString("venue_name");
                int count = rs.getInt("count");
                chartData.add(new PieChart.Data(venueName, count));
            }

        } catch (Exception e) {
            e.printStackTrace();
        }

        venuePieChart.setData(chartData);
    }

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene(venuePieChart, "ManagerDashboard.fxml");
    }
}
