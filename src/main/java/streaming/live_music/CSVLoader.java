package streaming.live_music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVLoader {
    private static final String FILE_PATH = "venues.csv";

    public static void loadVenuesFromCSV() {
        String insertSQL = "INSERT INTO venues (name, capacity, suitable_for, category, booking_price) VALUES (?, ?, ?, ?, ?)";

        try (BufferedReader br = new BufferedReader(new FileReader(FILE_PATH));
             Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            String line;
            br.readLine(); // Skip header
            while ((line = br.readLine()) != null) {
                String[] data = line.split(",");

                if (data.length == 5) {
                    stmt.setString(1, data[0]); // Venue Name
                    stmt.setInt(2, Integer.parseInt(data[1])); // Capacity
                    stmt.setString(3, data[2]); // Suitable For
                    stmt.setString(4, data[3]); // Category
                    stmt.setDouble(5, Double.parseDouble(data[4])); // Booking Price

                    stmt.executeUpdate();
                }
            }
            System.out.println("CSV Data Successfully Loaded into Database.");
        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}