package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class VenueDAO {

    // Add a new venue to the database
    public static boolean addVenue(Venue venue) {
        String insertSQL = "INSERT INTO venues (name, capacity, suitable_for, category, booking_price) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {

            stmt.setString(1, venue.getName());
            stmt.setInt(2, venue.getCapacity());
            stmt.setString(3, venue.getSuitableFor());
            stmt.setString(4, venue.getCategory());
            stmt.setDouble(5, venue.getBookingPrice());

            int rowsInserted = stmt.executeUpdate();
            System.out.println("Venue inserted successfully: " + venue.getName());
            return rowsInserted > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    // Retrieve all venues from the database
    public static List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String query = "SELECT name, capacity, suitable_for, category, booking_price FROM venues";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query);
             ResultSet rs = stmt.executeQuery()) {

            while (rs.next()) {
                venues.add(new Venue(
                        rs.getString("name"),
                        rs.getInt("capacity"),
                        rs.getString("suitable_for"),
                        rs.getString("category"),
                        rs.getDouble("booking_price")
                ));
            }
            System.out.println("Venues fetched successfully from database.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return venues;
    }
}
