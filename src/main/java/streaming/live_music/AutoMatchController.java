package streaming.live_music;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.util.ArrayList;
import java.util.List;

public class AutoMatchController {

    @FXML
    private TextArea resultArea;

    @FXML
    private void handleBack(ActionEvent event) {
        Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
        SceneSwitcher.switchScene(stage, "/streaming/live_music/hello-view.fxml");
    }

    @FXML
    private void handleStartMatching(ActionEvent event) {
        List<String> matches = startMatching();
        resultArea.clear();

        // Display matches in the TextArea
        for (String match : matches) {
            resultArea.appendText(match + "\n");
        }
    }

    private List<String> startMatching() {
        List<String> matchResults = new ArrayList<>();

        // Sample matching logic (modify as needed)
        for (Venue venue : DataStore.venues) {
            if (venue.getCapacity() >= 500) {
                matchResults.add("Matched venue: " + venue.getName() + " (Capacity: " + venue.getCapacity() + ")");
            } else {
                matchResults.add("No match found for venue: " + venue.getName());
            }
        }

        return matchResults;
    }
}
