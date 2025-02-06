package streaming.live_music;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class VenueDAO {

    public List<Venue> getAllVenues() {
        List<Venue> venues = new ArrayList<>();
        String query = "SELECT name, location, capacity, eventType FROM venues";

        try (Connection conn = DriverManager.getConnection("jdbc:sqlite:lmvm_database.db");
             PreparedStatement pstmt = conn.prepareStatement(query);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                venues.add(new Venue(
                        rs.getString("name"),
                        rs.getString("location"),
                        rs.getInt("capacity"),
                        rs.getString("eventType")
                ));
            }

        } catch (SQLException e) {
            Logger.getLogger(VenueDAO.class.getName()).log(Level.SEVERE, null, e);
        }
        return venues;
    }
}
