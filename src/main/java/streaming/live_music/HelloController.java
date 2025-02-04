package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import streaming.live_music.SceneSwitcher;

public class HelloController {

    @FXML
    private void handleJobRequests(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void handleOrderSummary(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/orderSummary.fxml");
    }

    @FXML
    private void handleVenueList(ActionEvent event) {
        SceneSwitcher.switchScene(event, "/streaming/live_music/venueList.fxml");
    }
}
