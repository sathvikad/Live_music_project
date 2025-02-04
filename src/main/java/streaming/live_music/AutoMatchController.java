package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;

public class AutoMatchController {

    @FXML
    public void handleBack(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/hello-view.fxml");
    }
}
