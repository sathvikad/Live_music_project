package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.stage.Stage;

public class RegisterController {

    @FXML
    private TextField usernameField;

    @FXML
    private PasswordField passwordField;

    @FXML
    private TextField firstNameField;

    @FXML
    private TextField lastNameField;

    @FXML
    private CheckBox isManagerCheckbox;

    @FXML
    private Label statusLabel;

    // Switch to login screen
    @FXML
    private void switchToLogin(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/login.fxml");
    }

    // Handle user registration
    @FXML
    private void handleRegister(ActionEvent event) {
        String username = usernameField.getText();
        String password = passwordField.getText();
        String firstName = firstNameField.getText();
        String lastName = lastNameField.getText();
        boolean isManager = isManagerCheckbox.isSelected();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty()) {
            statusLabel.setText("All fields must be filled!");
            return;
        }

        if (DataStore.addUser(username, password, firstName, lastName, isManager)) {
            statusLabel.setText("Registration successful. Redirecting to login...");
            switchToLogin(event);
        } else {
            statusLabel.setText("Username already exists. Try a different one.");
        }
    }
}
