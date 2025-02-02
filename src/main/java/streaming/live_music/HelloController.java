package streaming.live_music;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.Window;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

public class HelloController {

    private static final Logger LOGGER = Logger.getLogger(HelloController.class.getName());

    @FXML
    public void handleJobRequests() {
        switchScene("/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    public void handleVenueList() {
        switchScene("/streaming/live_music/venueList.fxml");
    }

    @FXML
    public void handleAutoMatch() {
        switchScene("/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    public void handleOrderSummary() {
        switchScene("/streaming/live_music/orderSummary.fxml");
    }

    public void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();

            Stage currentStage = (Stage) Stage.getWindows().stream()
                    .filter(Window::isShowing)
                    .findFirst()
                    .orElseThrow(() -> new IllegalStateException("No stage is currently showing"));

            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            LOGGER.log(Level.SEVERE, "Failed to switch scene to " + fxmlFile, e);
        }
    }
}
