package streaming.live_music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize the database
        streaming.live_music.DatabaseInitializer.initialize();

        // Load the login screen
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/streaming/live_music/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load(), 800, 600);
        primaryStage.setTitle("Live Music Venue Matchmaker");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch();
    }
}
