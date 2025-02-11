package streaming.live_music;

import javafx.application.Application;
import javafx.stage.Stage;

public class HelloApplication extends Application {

    @Override
    public void start(Stage primaryStage) {
        SceneSwitcher.setStage(primaryStage);
        SceneSwitcher.switchScene("/streaming/live_music/login.fxml");
    }

    public static void main(String[] args) {
        DatabaseInitializer.initialize();  // Initialize database tables
        CSVImporter.importVenues();        // Import CSV data into venues table
        CSVImporter.importRequests();      // Import CSV data into requests table
        DataChecker.checkImportedData();   // Verify the data import
        launch();                          // Start the application
    }
}
