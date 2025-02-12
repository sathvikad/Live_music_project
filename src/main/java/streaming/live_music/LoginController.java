package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.event.ActionEvent;
import javafx.scene.Node;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private void handleLogin(ActionEvent event) {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();

        if (username.isEmpty() || password.isEmpty()) {
            showAlert("Login Error", "Please enter both username and password.");
            return;
        }

        if (validateCredentials(username, password)) {
            showAlert("Success", "Login successful!");
            SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");
        } else {
            showAlert("Login Error", "Invalid username or password.");
        }
    }

    private boolean validateCredentials(String username, String password) {
        try (BufferedReader br = new BufferedReader(new FileReader("users.txt"))) {
            String line;
            while ((line = br.readLine()) != null) {
                String[] userDetails = line.split(",");
                if (userDetails.length == 2 && userDetails[0].trim().equals(username) && userDetails[1].trim().equals(password)) {
                    return true;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            showAlert("File Error", "Could not read user database.");
        }
        return false;
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