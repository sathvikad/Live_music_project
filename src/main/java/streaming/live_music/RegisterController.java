package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

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
    private ComboBox<String> roleComboBox;

    private final String userFilePath = System.getProperty("user.dir") + "/src/main/resources/streaming/live_music/users.txt";

    @FXML
    private void initialize() {
        roleComboBox.getItems().addAll("Manager", "Staff");
    }

    @FXML
    private void handleRegister() {
        String username = usernameField.getText().trim();
        String password = passwordField.getText().trim();
        String firstName = firstNameField.getText().trim();
        String lastName = lastNameField.getText().trim();
        String role = roleComboBox.getValue();

        if (username.isEmpty() || password.isEmpty() || firstName.isEmpty() || lastName.isEmpty() || role == null) {
            showAlert("Registration Error", "Please fill in all fields.");
            return;
        }

        try (BufferedWriter writer = new BufferedWriter(new FileWriter(userFilePath, true))) {
            writer.write(username + "," + password + "," + firstName + "," + lastName + "," + role + "\n");
            showAlert("Success", "Registration successful!");
            handleBackToLogin();  // Go back to the login screen after successful registration
        } catch (IOException e) {
            showAlert("Error", "Unable to save user data.");
        }
    }

    @FXML
    private void handleBackToLogin() {
        SceneSwitcher.switchScene("/streaming/live_music/login.fxml");
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.showAndWait();
    }
}
