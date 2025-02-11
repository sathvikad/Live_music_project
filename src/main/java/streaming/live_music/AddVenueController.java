package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class AddVenueController {

    @FXML
    private TextField venueNameField;
    @FXML
    private TextField capacityField;
    @FXML
    private TextField suitableForField;
    @FXML
    private TextField categoryField;
    @FXML
    private TextField bookingPriceField;
    @FXML
    private Button submitButton;
    @FXML
    private Button backButton;

    @FXML
    private void handleAddVenue() {
        try {
            System.out.println("Attempting to add venue...");

            String name = venueNameField.getText().trim();
            int capacity = Integer.parseInt(capacityField.getText().trim());
            String suitableFor = suitableForField.getText().trim();
            String category = categoryField.getText().trim();
            double bookingPrice = Double.parseDouble(bookingPriceField.getText().trim());

            System.out.println("Venue Name: " + name);
            System.out.println("Capacity: " + capacity);
            System.out.println("Suitable For: " + suitableFor);
            System.out.println("Category: " + category);
            System.out.println("Booking Price: " + bookingPrice);

            Venue newVenue = new Venue(name, capacity, suitableFor, category, bookingPrice);
            boolean isInserted = VenueDAO.addVenue(newVenue);

            if (isInserted) {
                showAlert("Success", "Venue added successfully!");
                clearFields();
            } else {
                showAlert("Error", "Failed to add venue.");
            }
        } catch (NumberFormatException e) {
            showAlert("Error", "Invalid input. Please enter valid numbers for capacity and booking price.");
        } catch (Exception e) {
            e.printStackTrace();
            showAlert("Error", "An unexpected error occurred.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }

    private void clearFields() {
        venueNameField.clear();
        capacityField.clear();
        suitableForField.clear();
        categoryField.clear();
        bookingPriceField.clear();
    }

    @FXML
    private void handleBackToDashboard() {
        SceneSwitcher.switchScene(backButton, "ManagerDashboard.fxml");
    }
}
