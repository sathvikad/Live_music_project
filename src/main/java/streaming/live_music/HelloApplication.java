package streaming.live_music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    @Override
    public void start(Stage stage) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/streaming/live_music/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Live Music Venue Matchmaker");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        // Initialize the database before launching the application
        DatabaseInitializer.initializeDatabase();

        // Start JavaFX application
        launch();
    }
}
