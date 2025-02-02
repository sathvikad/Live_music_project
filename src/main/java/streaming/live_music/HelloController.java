package streaming.live_music;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    public void switchScene(String fxmlFile, Stage currentStage) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Scene scene = new Scene(root);
            currentStage.setScene(scene);
            currentStage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void handleJobRequests() {
        Stage currentStage = getCurrentStage();
        switchScene("/streaming/live_music/jobRequest.fxml", currentStage);
    }

    public void handleVenueList() {
        Stage currentStage = getCurrentStage();
        switchScene("/streaming/live_music/venueList.fxml", currentStage);
    }

    public void handleAutoMatch() {
        Stage currentStage = getCurrentStage();
        switchScene("/streaming/live_music/autoMatch.fxml", currentStage);
    }

    public void handleOrderSummary() {
        Stage currentStage = getCurrentStage();
        switchScene("/streaming/live_music/orderSummary.fxml", currentStage);
    }

    private Stage getCurrentStage() {
        return (Stage) Stage.getWindows().stream()
                .filter(window -> window.isShowing())
                .findFirst()
                .orElse(null);
    }
}
