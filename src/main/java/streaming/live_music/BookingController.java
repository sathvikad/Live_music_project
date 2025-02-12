package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.time.LocalDate;

public class BookingController {

    @FXML
    private TextField jobRequestIdField;
    @FXML
    private TextField venueNameField;
    @FXML
    private DatePicker bookingDateField;
    @FXML
    private Label bookingStatusLabel;
    @FXML
    private Button bookVenueButton;
    @FXML
    private Button backButton;

    @FXML
    public void handleBookVenue() {
        try {
            String jobRequestId = jobRequestIdField.getText().trim();
            String venueName = venueNameField.getText().trim();
            LocalDate bookingDate = bookingDateField.getValue();

            if (jobRequestId.isEmpty() || venueName.isEmpty() || bookingDate == null) {
                bookingStatusLabel.setText("Please fill all fields.");
                return;
            }

            // Insert booking into the database
            String insertSQL = "INSERT INTO bookings (job_request_id, venue_name, booking_date) VALUES (?, ?, ?)";
            try (Connection conn = DatabaseInitializer.getConnection();
                 PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

                stmt.setInt(1, Integer.parseInt(jobRequestId));
                stmt.setString(2, venueName);
                stmt.setString(3, bookingDate.toString());

                int rowsAffected = stmt.executeUpdate();
                if (rowsAffected > 0) {
                    bookingStatusLabel.setText("Booking Successful!");
                } else {
                    bookingStatusLabel.setText("Booking Failed!");
                }
            }
        } catch (SQLException e) {
            bookingStatusLabel.setText("Error booking venue!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard() {
        if (backButton == null) {
            System.out.println("Error: Back button is null in BookingController!");
            return;
        }
        SceneSwitcher.switchScene(backButton, "ManagerDashboard.fxml");
    }
}
