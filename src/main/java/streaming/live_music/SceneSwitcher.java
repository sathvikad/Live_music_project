package streaming.live_music;

import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;

public class SceneSwitcher {

    public static void switchScene(Node eventSource, String fxmlFilePath) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(SceneSwitcher.class.getResource(fxmlFilePath));
            Parent root = fxmlLoader.load();
            Stage stage = (Stage) eventSource.getScene().getWindow();
            stage.setScene(new Scene(root));
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error loading scene: " + fxmlFilePath);
        }
    }
}
