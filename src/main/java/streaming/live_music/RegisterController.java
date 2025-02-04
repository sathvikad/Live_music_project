package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String role = "staff";  // Default role for registration

        if (userDAO.registerUser(username, password, role)) {
            showAlert("Success", "Registration successful! You can now log in.");
            SceneSwitcher.switchScene(event, "/streaming/live_music/login.fxml");
        } else {
            showAlert("Error", "Registration failed. Username might already be taken.");
        }
    }

    @FXML
    public void switchToLogin(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/login.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
