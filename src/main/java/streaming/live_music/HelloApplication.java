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
        // Step 1: Initialize database (Ensures tables exist)
        DatabaseInitializer.initializeDatabase();

        // Step 2: Import CSV Data (Venues & Job Requests)
        importCSVData();

        // Step 3: Launch JavaFX Application
        launch();
    }

    private static void importCSVData() {
        try {
            // Import Venues
            String venuesCSVPath = "src/main/resources/streaming/live_music/venues.csv";
            CSVImporter.importVenues(venuesCSVPath);
            System.out.println("Venues imported successfully.");

            // Import Job Requests
            String jobRequestsCSVPath = "src/main/resources/streaming/live_music/requests.csv";
            CSVImporter.importJobRequests(jobRequestsCSVPath);
            System.out.println("Job requests imported successfully.");

        } catch (Exception e) {
            System.err.println("Error importing CSV files!");
            e.printStackTrace();
        }
    }
}
