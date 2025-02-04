package streaming.live_music;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {

    private static Stage primaryStage;

    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Set the static reference
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("/streaming/live_music/login.fxml"));
        Scene scene = new Scene(fxmlLoader.load());
        stage.setTitle("Live Music Venue Matchmaker");
        stage.setScene(scene);
        stage.show();
    }

    public static Stage getPrimaryStage() {
        return primaryStage;
    }

    public static void main(String[] args) {
        launch();
    }
}
