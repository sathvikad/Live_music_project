package streaming.live_music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenueDAO {

    private static final Logger LOGGER = Logger.getLogger(VenueDAO.class.getName());

    /**
     * Fetches all venues from the database.
     *
     * @return List of Venue objects.
     */
    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String query = "SELECT name, location, capacity, eventType FROM venues";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            if (conn == null) {
                LOGGER.log(Level.SEVERE, "Connection to the database failed.");
                return venues;
            }

            while (rs.next()) {
                Venue venue = new Venue(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("capacity"),
                        rs.getString("eventType")
                );
                venues.add(venue);
            }

        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving venues from the database.", e);
        }

        return venues;
    }

    /**
     * Inserts a new venue into the database.
     *
     * @param venue The Venue object to insert.
     * @return true if the venue is successfully added, otherwise false.
     */
    public boolean addVenue(Venue venue) {
        String query = "INSERT INTO venues (name, location, capacity, eventType) VALUES (?, ?, ?, ?)";

        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            if (conn == null) {
                LOGGER.log(Level.SEVERE, "Connection to the database failed.");
                return false;
            }

            pstmt.setString(1, venue.getName());
            pstmt.setString(2, venue.getLocation());
            pstmt.setInt(3, venue.getCapacity());
            pstmt.setString(4, venue.getEventType());

            int affectedRows = pstmt.executeUpdate();
            if (affectedRows > 0) {
                LOGGER.log(Level.INFO, "Venue added successfully: {0}", venue.getName());
                return true;
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error inserting venue into the database.", e);
        }
        return false;
    }
}
