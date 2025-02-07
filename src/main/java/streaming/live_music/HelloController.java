package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class HelloController {

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void switchToRegistration(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/register.fxml");
    }
}
