package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class VenueDAO {

    private final Connection conn;

    public VenueDAO(Connection conn) {
        this.conn = conn;
    }

    public void addVenue(Venue venue) {
        String query = "INSERT INTO venues (name, capacity, suitable_for, category) VALUES (?, ?, ?, ?)";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, venue.getName());
            stmt.setInt(2, venue.getCapacity());
            stmt.setString(3, venue.getSuitableFor());
            stmt.setString(4, venue.getCategory());
            stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
