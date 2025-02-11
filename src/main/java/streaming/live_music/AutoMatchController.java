package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class AutoMatchController {

    private static final Logger LOGGER = Logger.getLogger(AutoMatchController.class.getName());
    private Connection conn;

    // No-argument constructor required by JavaFX
    public AutoMatchController() {
        this.conn = DatabaseInitializer.getConnection();
    }

    public AutoMatchController(Connection connection) {
        this.conn = connection;
    }

    @FXML
    private void handleAutoMatch() {
        if (conn == null) {
            showAlert("Database Connection Error", "Unable to establish a database connection.");
            LOGGER.log(Level.SEVERE, "Database connection is null. Aborting auto-match.");
            return;
        }

        LOGGER.info("Starting auto-match...");
        autoMatchEventsToVenues();
        showAlert("Auto-Match Completed", "Auto-matching of events to venues is complete. Check the logs for results.");
    }

    public void autoMatchEventsToVenues() {
        String query = "SELECT * FROM requests";
        try (PreparedStatement statement = conn.prepareStatement(query);
             ResultSet resultSet = statement.executeQuery()) {

            boolean matched = false;
            while (resultSet.next()) {
                String eventTitle = resultSet.getString("title");
                String eventType = resultSet.getString("type");
                int audienceSize = resultSet.getInt("targetAudience");

                boolean isMatched = matchEventWithVenue(eventTitle, eventType, audienceSize);
                if (isMatched) {
                    matched = true;
                }
            }

            if (!matched) {
                LOGGER.warning("No events were successfully matched to venues.");
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error while auto-matching events to venues", e);
        }
    }

    private boolean matchEventWithVenue(String eventTitle, String eventType, int audienceSize) {
        String venueQuery = "SELECT * FROM venues WHERE suitable_for LIKE ? AND capacity >= ? ORDER BY capacity ASC LIMIT 1";

        try (PreparedStatement statement = conn.prepareStatement(venueQuery)) {
            statement.setString(1, "%" + eventType + "%");
            statement.setInt(2, audienceSize);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String venueName = resultSet.getString("name");
                    LOGGER.log(Level.INFO, "Event '{0}' matched with venue '{1}'", new Object[]{eventTitle, venueName});
                    return true;
                } else {
                    LOGGER.log(Level.WARNING, "No suitable venue found for event '{0}'", eventTitle);
                }
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error during venue matching for event: " + eventTitle, e);
        }

        return false;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
