package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import streaming.live_music.SceneSwitcher;

public class AutoMatchController {

    @FXML
    private void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/hello-view.fxml");
    }
}
