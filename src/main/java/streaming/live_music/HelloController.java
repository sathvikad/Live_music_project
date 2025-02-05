package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.stage.Stage;

public class HelloController {

    @FXML
    private void handleJobRequests(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/autoMatch.fxml");
    }
}
