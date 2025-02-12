package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.control.ComboBox;
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
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private ComboBox<String> roleComboBox;  // Role selection dropdown

    private static final String DB_URL = "jdbc:sqlite:lmvm_database.db"; // Updated DB name

    @FXML
    public void initialize() {
        roleComboBox.getItems().addAll("Manager", "Staff");  // Populate role dropdown
    }

    @FXML
    private void handleRegister(ActionEvent event) {
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String role = roleComboBox.getValue();

        if (firstName.isEmpty() || lastName.isEmpty() || username.isEmpty() || password.isEmpty() || role == null) {
            showAlert("Error", "Please fill all the fields and select a role.");
            return;
        }

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = connection.prepareStatement(
                     "INSERT INTO Users (firstName, lastName, username, password, role) VALUES (?, ?, ?, ?, ?)")) { // Use 'Users'

            stmt.setString(1, firstName);
            stmt.setString(2, lastName);
            stmt.setString(3, username);
            stmt.setString(4, password);
            stmt.setString(5, role);
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
