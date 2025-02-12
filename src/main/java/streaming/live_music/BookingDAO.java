package streaming.live_music;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BookingDAO {

    public static boolean isVenueAvailable(String venueName, String bookingDate) {
        String query = "SELECT COUNT(*) FROM bookings WHERE venue_name = ? AND booking_date = ?";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, venueName);
            stmt.setString(2, bookingDate);
            ResultSet rs = stmt.executeQuery();
            return rs.getInt(1) == 0; // Returns true if venue is available
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    public static void bookVenue(int jobRequestId, String venueName, String bookingDate, double totalPrice, double commission) {
        String insertSQL = "INSERT INTO bookings (job_request_id, venue_name, booking_date, total_price, commission) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = DatabaseInitializer.getConnection();
             PreparedStatement stmt = conn.prepareStatement(insertSQL)) {
            stmt.setInt(1, jobRequestId);
            stmt.setString(2, venueName);
            stmt.setString(3, bookingDate);
            stmt.setDouble(4, totalPrice);
            stmt.setDouble(5, commission);
            stmt.executeUpdate();
            System.out.println("Venue booked successfully!");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
