package streaming.live_music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) {
        try {
            SceneSwitcher.setStage(stage);

            CSVImporter.importVenues();
            CSVImporter.importRequests();

            FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/streaming/live_music/login.fxml"));
            Scene scene = new Scene(fxmlLoader.load(), 600, 400);
            stage.setTitle("Live Music Venue Matchmaker");
            stage.setScene(scene);
            stage.show();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        launch();
    }
}
