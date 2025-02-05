package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.stage.Stage;

public class LoginController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private Label errorLabel;

    // Handle login
    public void handleLogin(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();

        // Basic user validation (replace with database validation if needed)
        User user = DataStore.users.get(username);

        if (user != null && user.getPassword().equals(password)) {
            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            if (user.isManager()) {
                SceneSwitcher.switchScene(stage, "/streaming/live_music/managerDashboard.fxml");
            } else {
                SceneSwitcher.switchScene(stage, "/streaming/live_music/staffDashboard.fxml");
            }
        } else {
            errorLabel.setText("Invalid credentials. Please try again.");
        }
    }

    // Handle switching to registration screen
    @FXML
    private void switchToRegister(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/register.fxml");
    }
}
