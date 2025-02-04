package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class AutoMatchController {

    @FXML
    private void handleBack(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/hello-view.fxml");
    }
}
