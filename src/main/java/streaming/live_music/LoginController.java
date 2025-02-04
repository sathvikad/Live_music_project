package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    private final UserDAO userDAO = new UserDAO();

    @FXML
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        if (userDAO.loginUser(username, password)) {
            SceneSwitcher.switchScene(event, "/streaming/live_music/staffDashboard.fxml");
        } else {
            showAlert("Login Failed", "Invalid username or password.");
        }
    }

    @FXML
    public void switchToRegister(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/register.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
