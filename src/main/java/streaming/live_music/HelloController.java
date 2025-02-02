package streaming.live_music;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class HelloController {

    private void switchScene(String fxmlFile) {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource(fxmlFile));
            Parent root = loader.load();
            Stage stage = (Stage) root.getScene().getWindow();
            stage.setScene(new Scene(root));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleJobRequests() {
        switchScene("/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleVenueList() {
        switchScene("/streaming/live_music/venueList.fxml");
    }

    @FXML
    private void handleAutoMatch() {
        switchScene("/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void handleOrderSummary() {
        switchScene("/streaming/live_music/orderSummary.fxml");
    }
}
