package streaming.live_music;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.input.MouseEvent;

public class HelloController {

    @FXML
    private void handleJobRequests(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/jobRequest.fxml");
    }

    @FXML
    private void handleAutoMatch(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/autoMatch.fxml");
    }

    @FXML
    private void handleOrderSummary(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/orderSummary.fxml");
    }

    @FXML
    private void handleVenueList(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/venueList.fxml");
    }
    @FXML
    private void handleOrderScreen(MouseEvent event) {
        SceneSwitcher.switchScene((Button) event.getSource(), "/streaming/live_music/order.fxml");
    }

}
