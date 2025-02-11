package streaming.live_music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {

    private static final String VENUE_CSV_FILE = "src/main/resources/streaming/live_music/venues.csv";
    private static final String REQUEST_CSV_FILE = "src/main/resources/streaming/live_music/requests.csv";

    public static void importVenues() {
        String insertSQL = "INSERT INTO venues (name, capacity, suitable_for, category) VALUES (?, ?, ?, ?)";
        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(VENUE_CSV_FILE));
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 4) {
                    System.err.println("Invalid venue data: " + line);
                    continue;
                }

                stmt.setString(1, data[0].trim());
                stmt.setInt(2, Integer.parseInt(data[1].trim()));
                stmt.setString(3, data[2].trim());
                stmt.setString(4, data[3].trim());
                stmt.executeUpdate();
            }
            System.out.println("Venues imported successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void importRequests() {
        String insertSQL = "INSERT INTO requests (client, title, artist, date, time, targetAudience, type, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(REQUEST_CSV_FILE));
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            String line = reader.readLine(); // Skip header
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 8) {
                    System.err.println("Invalid request data: " + line);
                    continue;
                }

                stmt.setString(1, data[0].trim());
                stmt.setString(2, data[1].trim());
                stmt.setString(3, data[2].trim());
                stmt.setString(4, data[3].trim());
                stmt.setString(5, data[4].trim());
                stmt.setInt(6, Integer.parseInt(data[5].trim()));
                stmt.setString(7, data[6].trim());
                stmt.setString(8, data[7].trim());
                stmt.executeUpdate();
            }
            System.out.println("Requests imported successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
