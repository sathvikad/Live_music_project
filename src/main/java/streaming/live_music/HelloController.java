package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloController {

    @FXML
    private void handleJobRequests(ActionEvent event) {
        loadScene("jobRequest.fxml");
    }

    @FXML
    private void handleVenues(ActionEvent event) {
        loadScene("venueList.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        loadScene("autoMatch.fxml");
    }

    @FXML
    private void handleOrderSummary(ActionEvent event) {
        loadScene("orderSummary.fxml");
    }

    private void loadScene(String fxmlFile) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource(fxmlFile));
            Scene scene = new Scene(fxmlLoader.load());
            Stage stage = (Stage) scene.getWindow();
            stage.setScene(scene);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    private void handleBackToDashboard(ActionEvent event) {
        loadScene("hello-view.fxml");
    }
}
