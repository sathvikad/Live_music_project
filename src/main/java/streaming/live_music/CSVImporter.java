package streaming.live_music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CSVImporter {

    /**
     * Imports venue data from a CSV file into the database.
     * Ensures no duplicate venues are inserted.
     */
    public static void importVenues(String csvFilePath) {
        String insertSQL = "INSERT INTO venues (name, capacity, suitable_for, category, booking_price) VALUES (?, ?, ?, ?, ?)";
        int count = 0;

        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            reader.readLine(); // Skip CSV header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) {
                    System.err.println("Skipping invalid venue row: " + line);
                    continue;
                }

                try {
                    String name = data[0].trim();
                    int capacity = Integer.parseInt(data[1].trim());
                    String suitableFor = data[2].trim();
                    String category = data[3].trim();
                    double bookingPrice = Double.parseDouble(data[4].trim());

                    // Check for duplicate venue entry
                    if (!isVenueExists(conn, name)) {
                        stmt.setString(1, name);
                        stmt.setInt(2, capacity);
                        stmt.setString(3, suitableFor);
                        stmt.setString(4, category);
                        stmt.setDouble(5, bookingPrice);
                        stmt.executeUpdate();
                        count++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid venue row (data format error): " + line);
                }
            }
            System.out.println("Venues imported successfully! New entries added: " + count);

        } catch (IOException | SQLException e) {
            System.err.println("Error importing venues!");
            e.printStackTrace();
        }
    }

    /**
     * Imports job requests from a CSV file into the database.
     * Ensures no duplicate job requests are inserted.
     */
    public static void importJobRequests(String csvFilePath) {
        String insertSQL = "INSERT INTO job_requests (client, title, artist, date, time, duration, target_audience, type, category) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";
        int count = 0;

        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            reader.readLine(); // Skip CSV header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 9) {
                    System.err.println("Skipping invalid job request row: " + line);
                    continue;
                }

                try {
                    String client = data[0].trim();
                    String title = data[1].trim();
                    String artist = data[2].trim();
                    String date = formatDate(data[3].trim()); // Ensures proper date format
                    String time = data[4].trim();
                    int duration = Integer.parseInt(data[5].trim());
                    int targetAudience = Integer.parseInt(data[6].trim());
                    String type = data[7].trim();
                    String category = data[8].trim();

                    // Check for duplicate job request entry
                    if (!isJobRequestExists(conn, title, date)) {
                        stmt.setString(1, client);
                        stmt.setString(2, title);
                        stmt.setString(3, artist);
                        stmt.setString(4, date);
                        stmt.setString(5, time);
                        stmt.setInt(6, duration);
                        stmt.setInt(7, targetAudience);
                        stmt.setString(8, type);
                        stmt.setString(9, category);
                        stmt.executeUpdate();
                        count++;
                    }
                } catch (NumberFormatException e) {
                    System.err.println("Skipping invalid job request row (data format error): " + line);
                }
            }
            System.out.println("Job requests imported successfully! New entries added: " + count);

        } catch (IOException | SQLException e) {
            System.err.println("Error importing job requests!");
            e.printStackTrace();
        }
    }

    /**
     * Checks if a venue already exists in the database.
     */
    private static boolean isVenueExists(Connection conn, String venueName) throws SQLException {
        String checkSQL = "SELECT COUNT(*) FROM venues WHERE name = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkSQL)) {
            stmt.setString(1, venueName);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        }
    }

    /**
     * Checks if a job request already exists in the database.
     */
    private static boolean isJobRequestExists(Connection conn, String title, String date) throws SQLException {
        String checkSQL = "SELECT COUNT(*) FROM job_requests WHERE title = ? AND date = ?";
        try (PreparedStatement stmt = conn.prepareStatement(checkSQL)) {
            stmt.setString(1, title);
            stmt.setString(2, date);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) > 0;
        }
    }

    /**
     * Formats date strings to YYYY-MM-DD.
     */
    private static String formatDate(String rawDate) {
        // Example: Converts 20-12-24 to 2024-12-20
        String[] parts = rawDate.split("-");
        if (parts.length == 3) {
            return "20" + parts[2] + "-" + parts[1] + "-" + parts[0];
        }
        return rawDate; // Return original if format is unknown
    }
}
