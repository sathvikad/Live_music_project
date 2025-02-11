package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class RegisterController {

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private TextField contactField;

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String DB_URL = "jdbc:sqlite:live_music_venues.db";

    @FXML
    private void handleRegister(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String contact = contactField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (firstName.isEmpty() || lastName.isEmpty() || contact.isEmpty() || username.isEmpty() || password.isEmpty()) {
            showAlert("Error", "Please fill all the fields.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO users (first_name, last_name, contact, username, password) VALUES (?, ?, ?, ?, ?)")) {

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, contact);
            stmt.setString(4, username);
            stmt.setString(5, password);
            stmt.executeUpdate();

            showAlert("Success", "Registration successful! You can now log in.");
            SceneSwitcher.switchScene((Node) event.getSource(), "Login.fxml");

        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not save user. Please try again.");
        }
    }

    @FXML
    private void handleBackToLogin(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "Login.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }
}
