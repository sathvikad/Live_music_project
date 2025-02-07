package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class LoginController {

    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }

        String role = validateCredentials(username, password);
        if (role != null) {
            System.out.println("Login successful: " + username + " as " + role);
            showAlert("Success", "Login successful.");
            if (role.equalsIgnoreCase("Manager")) {
                SceneSwitcher.switchScene(event, "/streaming/live_music/managerDashboard.fxml");
            } else if (role.equalsIgnoreCase("Staff")) {
                SceneSwitcher.switchScene(event, "/streaming/live_music/staffDashboard.fxml");
            }
        } else {
            showAlert("Login Error", "Invalid username or password.");
        }
    }

    private String validateCredentials(String username, String password) {
        try (Scanner scanner = new Scanner(new File("users.txt"))) {
            while (scanner.hasNextLine()) {
                String[] userDetails = scanner.nextLine().split(",");
                if (userDetails[0].equals(username) && userDetails[1].equals(password)) {
                    return userDetails[4];
                }
            }
        } catch (IOException e) {
            showAlert("Error", "Unable to read user data.");
        }
        return null;
    }

    @FXML
    private void handleSignUpRedirect(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/register.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
