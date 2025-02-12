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
import java.sql.ResultSet;
import java.sql.SQLException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private static final String DB_URL = "jdbc:sqlite:lmvm_database.db"; // Updated DB name

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }

        String role = validateCredentials(username, password);
        if (role != null) {
            showAlert("Success", "Login successful!");

            if (role.equals("Manager")) {
                SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
            } else {
                SceneSwitcher.switchScene((Node) event.getSource(), "StaffDashboard.fxml");
            }
        } else {
            showAlert("Login Error", "Invalid username or password.");
        }
    }

    private String validateCredentials(String username, String password) {
        String query = "SELECT role FROM Users WHERE username = ? AND password = ?"; // Use 'Users'

        try (Connection connection = DriverManager.getConnection(DB_URL);
             PreparedStatement stmt = connection.prepareStatement(query)) {

            stmt.setString(1, username);
            stmt.setString(2, password);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                return rs.getString("role"); // Return role if credentials are valid
            }
        } catch (SQLException e) {
            e.printStackTrace();
            showAlert("Database Error", "Could not connect to the database.");
        }
        return null;
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void handleSignUpRedirect(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "Register.fxml");
    }
}
