package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.event.ActionEvent;

public class BookingController {

    @FXML private TextField jobRequestIdField;
    @FXML private TextField venueNameField;
    @FXML private DatePicker bookingDatePicker;
    @FXML private Label statusLabel;

    @FXML
    private void handleBookVenue(ActionEvent event) {
        try {
            int jobRequestId = Integer.parseInt(jobRequestIdField.getText());
            String venueName = venueNameField.getText();
            String bookingDate = bookingDatePicker.getValue().toString();

            if (!BookingDAO.isVenueAvailable(venueName, bookingDate)) {
                statusLabel.setText("Venue is already booked!");
                return;
            }

            double totalPrice = 5000.0; // Dummy value, can be calculated
            double commission = totalPrice * 0.10; // 10% brokerage fee

            BookingDAO.bookVenue(jobRequestId, venueName, bookingDate, totalPrice, commission);
            statusLabel.setText("Venue booked successfully!");

        } catch (Exception e) {
            statusLabel.setText("Invalid input!");
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((javafx.scene.Node) event.getSource(), "ManagerDashboard.fxml");
    }
}
