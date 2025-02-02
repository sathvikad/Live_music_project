package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.input.MouseEvent;

public class AutoMatchController {

    @FXML
    private void handleBack(MouseEvent event) {
        HelloController controller = new HelloController();
        controller.switchScene("/streaming/live_music/hello-view.fxml");
    }
}
