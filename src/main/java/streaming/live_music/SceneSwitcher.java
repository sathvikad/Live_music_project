package streaming.live_music;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Node node, String fxmlFile) {
        if (node == null) {
            System.err.println("Error: SceneSwitcher received a null node!");
            return;
        }

        try {
            Stage stage = (Stage) node.getScene().getWindow();
            Parent root = FXMLLoader.load(SceneSwitcher.class.getResource(fxmlFile));
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            System.err.println("Error loading scene: " + fxmlFile);
            e.printStackTrace();
        }
    }
}