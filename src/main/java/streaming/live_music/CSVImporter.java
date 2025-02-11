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
        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(VENUE_CSV_FILE))) {

            String line;
            reader.readLine(); // Skip the header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 4) {
                    System.err.println("Invalid venue data (incorrect columns): " + line);
                    continue;
                }

                String name = data[0].trim();
                String location = "Melbourne";  // Default location
                int capacity = Integer.parseInt(data[1].trim());
                String category = data[3].trim();
                String eventType = data[2].trim().split(";")[0].trim();  // Take the first event type

                String insertSQL = "INSERT INTO venues (name, location, capacity, category, eventType) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setString(1, name);
                    stmt.setString(2, location);
                    stmt.setInt(3, capacity);
                    stmt.setString(4, category);
                    stmt.setString(5, eventType);
                    stmt.executeUpdate();
                }
            }
            System.out.println("Venues imported successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    public static void importRequests() {
        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(REQUEST_CSV_FILE))) {

            String line;
            reader.readLine(); // Skip the header

            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length < 7) {
                    System.err.println("Invalid request data (incorrect columns): " + line);
                    continue;
                }

                String clientName = data[0].trim();
                String eventName = data[1].trim();
                String eventDate = formatDate(data[3].trim());
                String eventTime = data[4].trim();
                int expectedAttendance = Integer.parseInt(data[5].trim());
                String eventType = data[6].trim();

                // Combine event date and time
                String eventDateTime = eventDate + " " + eventTime;

                String insertSQL = "INSERT INTO requests (clientName, eventName, eventDateTime, expectedAttendance, eventType) VALUES (?, ?, ?, ?, ?)";
                try (PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
                    stmt.setString(1, clientName);
                    stmt.setString(2, eventName);
                    stmt.setString(3, eventDateTime);
                    stmt.setInt(4, expectedAttendance);
                    stmt.setString(5, eventType);
                    stmt.executeUpdate();
                }
            }
            System.out.println("Requests imported successfully.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }

    // Utility function to format date
    private static String formatDate(String date) {
        // Convert "DD-MM-YY" to "YYYY-MM-DD"
        if (date.matches("\\d{2}-\\d{2}-\\d{2}")) {
            String[] parts = date.split("-");
            return "20" + parts[2] + "-" + parts[1] + "-" + parts[0];
        }
        return date;  // Return as-is if already in the correct format
    }
}
