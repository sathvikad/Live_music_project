package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.scene.control.ComboBox;
import javafx.event.ActionEvent;
import javafx.scene.Node;

public class UpdateProfileController {
    private User loggedInUser;

    @FXML
    private TextField firstNameField;
    @FXML
    private TextField lastNameField;
    @FXML
    private TextField usernameField;
    @FXML
    private PasswordField passwordField;
    @FXML
    private ComboBox<String> roleComboBox;

    public void setUserData(User user) {
        this.loggedInUser = user;
        if (user != null) {
            firstNameField.setText(user.getFirstName());
            lastNameField.setText(user.getLastName());
            usernameField.setText(user.getUsername());
            passwordField.setText(user.getPassword());
            roleComboBox.setValue(user.getRole());  // Fix: Now uses getRole()
        }
    }

    @FXML
    private void handleUpdateProfile(ActionEvent event) {
        if (loggedInUser == null) {
            showAlert("Error", "No user loaded for update.");
            return;
        }

        String newFirstName = firstNameField.getText().trim();
        String newLastName = lastNameField.getText().trim();
        String newPassword = passwordField.getText().trim();
        String newRole = roleComboBox.getValue();

        if (newFirstName.isEmpty() || newLastName.isEmpty() || newPassword.isEmpty() || newRole.isEmpty()) {
            showAlert("Error", "Please fill all fields.");
            return;
        }

        boolean success = DataStore.updateUser(loggedInUser.getId(), newPassword, newFirstName, newLastName, newRole);  // Fix: Uses getId()
        if (success) {
            showAlert("Success", "Profile updated successfully.");
        } else {
            showAlert("Error", "Failed to update profile.");
        }
    }

    private void showAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setContentText(message);
        alert.setHeaderText(null);
        alert.showAndWait();
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        SceneSwitcher.switchScene((Node) event.getSource(), "ManagerDashboard.fxml");  // Fix: Passes correct argument
    }
}
