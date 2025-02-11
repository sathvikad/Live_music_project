package streaming.live_music;

import javafx.fxml.FXML;

public class HelloController {

    @FXML
    private void handleJobRequests() {
        SceneSwitcher.switchScene("/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch() {
        SceneSwitcher.switchScene("/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void switchToRegistration() {
        SceneSwitcher.switchScene("/streaming/live_music/registration.fxml");
    }
}
