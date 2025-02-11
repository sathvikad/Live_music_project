package streaming.live_music;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.chart.BarChart;
import javafx.scene.chart.XYChart;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class IncomeChartController {

    @FXML
    private BarChart<String, Number> incomeChart;

    @FXML
    public void initialize() {
        loadIncomeChartData();
    }

    private void loadIncomeChartData() {
        XYChart.Series<String, Number> incomeSeries = new XYChart.Series<>();
        incomeSeries.setName("Total Income ($)");

        XYChart.Series<String, Number> commissionSeries = new XYChart.Series<>();
        commissionSeries.setName("Commission ($)");

        String query = "SELECT event_title, SUM(booking_price) AS total_income, COUNT(client) AS client_orders " +
                "FROM bookings JOIN venues ON bookings.venue_name = venues.name " +
                "GROUP BY event_title, client";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                String eventTitle = rs.getString("event_title");
                double totalIncome = rs.getDouble("total_income");
                int clientOrders = rs.getInt("client_orders");

                double commission = (clientOrders > 1) ? totalIncome * 0.09 : totalIncome * 0.10;

                incomeSeries.getData().add(new XYChart.Data<>(eventTitle, totalIncome));
                commissionSeries.getData().add(new XYChart.Data<>(eventTitle, commission));
            }

            incomeChart.getData().addAll(incomeSeries, commissionSeries);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
