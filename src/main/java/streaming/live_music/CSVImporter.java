package streaming.live_music;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CSVImporter {

    public static void importVenues(String csvFilePath) {
        String insertSQL = "INSERT INTO venues (name, capacity, suitable_for, category, booking_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             BufferedReader reader = new BufferedReader(new FileReader(csvFilePath));
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            reader.readLine(); // Skip header
            String line;
            while ((line = reader.readLine()) != null) {
                String[] data = line.split(",");
                if (data.length < 5) continue; // Skip invalid lines

                String name = data[0].trim();
                int capacity = Integer.parseInt(data[1].trim());
                String suitableFor = data[2].trim();
                String category = data[3].trim();
                double bookingPrice = Double.parseDouble(data[4].trim());

                stmt.setString(1, name);
                stmt.setInt(2, capacity);
                stmt.setString(3, suitableFor);
                stmt.setString(4, category);
                stmt.setDouble(5, bookingPrice);

                stmt.executeUpdate();
            }
            System.out.println("Venues imported successfully!");

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        }
    }
}
