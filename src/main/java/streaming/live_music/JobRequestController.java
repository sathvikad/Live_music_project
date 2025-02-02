package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

public class JobRequestController {

    @FXML
    private void handleBack(MouseEvent event) {
        HelloController controller = new HelloController();

        // Get the current stage from the event source
        Stage currentStage = (Stage) ((Node) event.getSource()).getScene().getWindow();

        // Pass both arguments (FXML file path and the current stage)
        controller.switchScene("/streaming/live_music/hello-view.fxml", currentStage);
    }
}
